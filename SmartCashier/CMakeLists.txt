# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html
# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION "${MIN_VER_CMAKE}" FATAL_ERROR)
set(CMAKE_VERBOSE_MAKEFILE on)
PROJECT(smartcashier)

set(SNPE_INC snpe/include)
set(OpenCV_DIR "/usr/local/opencv/share/OpenCV/")
find_package(OpenCV REQUIRED)
message(STATUS "OpenCV library status:")
message(STATUS "    version: ${OpenCV_VERSION}")
message(STATUS "    libraries: ${OpenCV_LIBS}")
message(STATUS "    include path: ${OpenCV_INCLUDE_DIRS}")

set(CMAKE_BUILD_TYPE "Debug")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -lm -pthread -DANDROID_TARGET -DSNPE_ENGINE_SUPPORTED -DLOG_SWITCH=0")
set(CMAKE_CXX_FLAGS_DEBUG "$ENV{CXXFLAGS} -O0 -Wall -g -ggdb")
set(CMAKE_CXX_FLAGS_RELEASE "$ENV{CXXFLAGS} -O3 -Wall")
set(EXECUTABLE_OUTPUT_PATH ${CMAKE_SOURCE_DIR}/bin)
set(LIBRARY_OUTPUT_PATH ${CMAKE_SOURCE_DIR}/lib/)

set(SRC_DIR  "./")
set(GSTREAMER_INCLUDE_DIRS "/usr/include/gstreamer-1.0;")
set(GSTREAMER_INCLUDE_DIRS ${GSTREAMER_INCLUDE_DIRS} "${CMAKE_SOURCE_DIR}/include/glib-2.0;")
set(GSTREAMER_INCLUDE_DIRS ${GSTREAMER_INCLUDE_DIRS} "${CMAKE_SOURCE_DIR}/include/glib-2.0/include;")
set(GSTREAMER_INCLUDE_DIRS ${GSTREAMER_INCLUDE_DIRS} "${CMAKE_SOURCE_DIR}/include/gobject;")
set(GSTREAMER_INCLUDE_DIRS ${GSTREAMER_INCLUDE_DIRS} "${CMAKE_SOURCE_DIR}/include/gst;")
set(GSTREAMER_INCLUDE_DIRS ${GSTREAMER_INCLUDE_DIRS} "/usr/lib/gstreamer-1.0/include;")
set(GSTREAMER_INCLUDE_DIRS ${GSTREAMER_INCLUDE_DIRS} "/usr/include/glib-2.0;")
set(GSTREAMER_INCLUDE_DIRS ${GSTREAMER_INCLUDE_DIRS} "/usr/lib/glib-2.0/include;")
set(GSTREAMER_LIBS  "gstapp-1.0;")
set(GSTREAMER_LIBS ${GSTREAMER_LIBS} "gstbase-1.0;")
set(GSTREAMER_LIBS ${GSTREAMER_LIBS} "gstrtsp-1.0;")
set(GSTREAMER_LIBS ${GSTREAMER_LIBS} "gstreamer-1.0;")
set(GSTREAMER_LIBS ${GSTREAMER_LIBS} "gstsdp-1.0;")
set(GSTREAMER_LIBS ${GSTREAMER_LIBS} "gio-2.0;")
set(GSTREAMER_LIBS ${GSTREAMER_LIBS} "gobject-2.0;")
set(GSTREAMER_LIBS ${GSTREAMER_LIBS} "glib-2.0;")
set(GSTREAMER_LIBRARY_DIRS  "/usr/lib/;")


set(GLIB_INC "${CMAKE_SOURCE_DIR}/glib-2.0/")

#OPENGL
set(OPENGL_LIBS  "GL;GLU;glut;GLEW;")
set(OPENCV33_LIBS  "opencv_core;opencv_imgproc;")
set(COMMON_LIBRARY_DIRS  "${CMAKE_SOURCE_DIR}/lib/;")

message(STATUS "    GST DEBUG:###########################")
message(STATUS "    libraries: ${GSTREAMER_LIBS}")
message(STATUS "    include path: ${GSTREAMER_INCLUDE_DIRS}")
message(STATUS "    library path: ${GSTREAMER_LIBRARY_DIRS}")

set(SRC_DIR "${SRC_DIR}/")
set(EXT_INC_DIR "${SRC_DIR}/include/")
set(INC_DIR "${SRC_DIR}/")
set(EXT_LIB_DIRS "${SRC_DIR}/lib/")
set(CURL "curl;") 
set(FIRE_INC "${SRC_DIR}/../")

# include_directories(
#         ${CMAKE_CURRENT_SOURCE_DIR}/include
# )
aux_source_directory(${SRC_DIR} DIR_SRCS)

include_directories(${SRC_DIR}/
	${EXT_INC_DIR}/
	${FIRE_INC}/
        ${GLIB_INC}/
        ${GSTREAMER_INCLUDE_DIRS}/
        ${GSTREAMER_APP_INCLUDE_DIRS}/
        ${GSTREAMER_RTSP_INCLUDE_DIRS}/
        ${INC_DIR}
        ${CMAKE_SOURCE_DIR}/include
        ${SNPE_INC}/zdl
        ${OpenCV_INCLUDE_DIRS}
)


find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log )

link_directories(
        ${GSTREAMER_LIBRARY_DIRS}
        ${EXT_LIB_DIRS}
)

add_executable( # Sets the name.
        ${PROJECT_NAME}

        # Sets the library as a shared library.
        #SHARED
        ${CMAKE_CURRENT_SOURCE_DIR}/src/ObjectDetection.cpp
        # Provides a relative path to your source file(s).

        ${CMAKE_CURRENT_SOURCE_DIR}/test/SmartCashier.cpp
)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
target_link_libraries(
        # Specifies the target library.
        ${PROJECT_NAME}
        ${GSTREAMER_LIBS}
        # snpe
        ${CMAKE_CURRENT_SOURCE_DIR}/lib/libSNPE.so
        # opencv
        ${OpenCV_LIBS}
        # zlib
        z
)
