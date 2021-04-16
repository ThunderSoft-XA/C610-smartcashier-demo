# Qualcomm® QCS610 SoC Open Kit SmartCashier Developer documentation

## brief introduction

This document mainly introduces the deployment and use of SmartCashierApp environment.Mainly contents as follow:

* Background
* Environment configuration
* Materials and Tools used for the project
* Install && Usage

## Background

Following the development of Embedded Machine,traditional Embedded Machine is difficult that coming intelligence behavior.So we need a new tool (e.g.Qualcomm Neural processing SDK for AI) that we can built a AI application in target machine easily.And,The trend of intelligence is more and more obvious.At the same time,Qualcomm have a serial of tools and plugins(e.g.qtimmfsrc).So,We have the idea of SmartCashier run in the Qualcomm® QCS610 SoC.

## Environment configuration

Requirements:

* [Qualcomm Neural processing SDK for AI Setup]("https://developer.qualcomm.com/docs/snpe/setup.html")

1. Download the Neural Processing SDK from here:
   [https://developer.qualcomm.com/software/qualcomm-neural-processing-sdk](https://developer.qualcomm.com/software/qualcomm-neural-processing-sdk)
2. Git clone this project link [https://github.com/ThunderSoft-XA/C610-smartcashier-demo/](https://github.com/ThunderSoft-XA/C610-smarttraffic-demo2.0/)
3. Move the Neural Processing SDK contents to <C610-smartcashier-demo/SmartCashier/snpe/>

* [Opencv 3.4.3](https://docs.opencv.org/3.4.3/d9/df8/tutorial_root.html)
* [GStreamer Plugins]("https://developer.qualcomm.com/qualcomm-robotics-rb5-kit/software-reference-manual/application-semantics/gstreamer-plugins")
* [Yocto build tool]("https://www.yoctoproject.org/")

## Materials and Tools used for the project

1. Hardware materials

Except for the development board,The following hardware materials are also needed:

* Type-C usb line

using the usb line to develop on Qualcomm® QCS610 SoC development board.

![usb line](./res/usb.png )

* Charger

Direct power supply for Qualcomm® QCS610 SoC development board

![charger](./res/charger.jpg )

## Install && Usage

1. Installing the ADB tool
2. Write .bb file, compile the executable file of samrtcashierapp
3. push full package to "Qualcomm® QCS610 SoC",enter SmartCashier directory,run Install.sh
4. start the SmartCashier according to the 《Qualcomm® QCS610 SoC-SmartCashierApp_UserGuide》document

### For exsample of BB complie file

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://{COMMON_LICENSE_DIR}/{LICENSE};md5=3775480a712fc46a69647678acb234cb"

inherit cmake

DESCRIPTION = "Recipe to provide thundercomm bins library"
LICENSE = "CLOSED"
SECTION = "SmartCashierApp"

#Dependencies.
DEPENDS := "opencv"
DEPENDS += "gstreamer1.0"
DEPENDS += "glib-2.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gstreamer1.0-plugins-qti-oss-mlmeta"
DEPENDS += "gstreamer1.0-plugins-qti-oss-tools"
DEPENDS += "gstreamer1.0-rtsp-server"

#FILESPATH =+ "${WORKSPACE}/video_ai/:"

DESCRIPTION = "Hello world in cmake"
#FILESPATH =+ "${WORKSPACE}:"
SRC_URI = "file://video_ai/SmartCashier/"

#PN = 'smartcashier'
#PV = '1'
#/home/username/dirpath/cs-610/apps_proc/src/video_ai
#FILESPATH =+ "{WORKSPACE}/video_ai/:"
FILESPATH =+ "/home/username/dirpath/cs-610/apps_proc/src/video_ai/:"
SRCREV = "${AUTOREV}"

#S = "${FILESPATH}/build"
#S = "/home/username/dirpath/cs-610/apps_proc/src/video_ai/build"
S = "/home/username/dirpath/cs-610/apps_proc/src/video_ai/SmartCashier/"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

INSANE_SKIP_${PN} = "ldflags file-rdeps dev-deps"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_compile[noexec] = "1"

FILES_{PN} += "${bindir}/*"

FILES_{PN} += "${libdir}/*"
PARALLEL_MAKEINST = ""
