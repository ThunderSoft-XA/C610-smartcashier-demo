#include <glib.h>
#include <gst/app/gstappsink.h>
#include <gst/gst.h>

#include "gstcamera.hpp"
#include "gstimg.hpp"
#include "configenv.hpp"
#include "detection.hpp"
#include "TimeUtil.h"

using namespace std;
using namespace cv;
using namespace objdec;

int main(int argc, char *argv[])
{
  string inputInfo;
  if(setCameraEnv()) {
    printf("camera env init failed\n");
    return -1;
  }
  system("source /etc/gstreamer1.0/set_gst_env.sh");

  GstCamera *myGstCamera = new GstCamera();
  DetProcess *myDetPro = new DetProcess();
  GstImg *myGstImg = new GstImg();

  myGstCamera->CreateCameraPipeline();

  myGstCamera->setPipelineState(GST_STATE_PLAYING);
  cout << "Please put the goods...." << endl;
  cout << "please confirm that all goods been scanned or not??(Y or N):" << endl;
  cin >> inputInfo;

  myGstCamera->sendEventAndGetInfo(gst_event_new_eos());
  
  cout << "enter img pipeline" << endl;
  myGstImg->CreateImgPipeline();
  //myGstCamera->VideoToImg(imgpath);
  cout << "start detection goods...." << endl;

  int i = 0;
  long start_pre = getCurrentTime_ms();
  while(myDetPro->getImg(i) == 0) {
    myDetPro->getResult();
    i++;
  }
  long diff_sum = getCurrentTime_ms() - start_pre;
  std::cout << "sum cost time=" << diff_sum << std::endl;

  /*test static orange.jpeg*/
  //myDetPro->getStaticImg();
  //myDetPro->getResult();
  i = 0;

  int price = 0;
  vector<string> strNum = {};
  for(i; i < myDetPro->goodsTotal; i++) {
    strNum.push_back(myDetPro->goodsNameSet[i]);
  }

  strNum.erase(unique(strNum.begin(), strNum.end()), strNum.end()); 

  cout << "goods info as follow:" << endl;
  for(i = 0; i < strNum.size(); i++) {     
    cout << strNum[i] << endl;
    int nPos = strNum[i].find("$");
    if(nPos != -1)
    {
      string str = strNum[i].substr(nPos+1,strNum[i].size());
      price += atoi(str.c_str());
    }
  }
  cout << "total price: $" << price << endl;
  myDetPro->~DetProcess();

  system("rm /data/detection/img/image*");

  return 0;
}