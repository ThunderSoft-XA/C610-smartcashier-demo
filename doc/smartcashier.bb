LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=3775480a712fc46a69647678acb234cb"

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
#/home/turbox/wuqx0806/cs-610/apps_proc/src/video_ai
#FILESPATH =+ "${WORKSPACE}/video_ai/:"
FILESPATH =+ "/home/turbox/wuqx0806/cs-610/apps_proc/src/video_ai/:"
SRCREV = "${AUTOREV}"

#S = "${FILESPATH}/build"
#S = "/home/turbox/wuqx0806/cs-610/apps_proc/src/video_ai/build"
S = "/home/turbox/wuqx0806/cs-610/apps_proc/src/video_ai/SmartCashier/"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

INSANE_SKIP_${PN} = "ldflags file-rdeps dev-deps"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_compile[noexec] = "1"

INSANE_SKIP_${PN}-dev += "dev-elf dev-deps"

do_install () {
    echo "--- examples doing install ---"
    echo ${D}
    echo ${bindir}
    echo ${D}${bindir}
    echo ${WORK_DIR}
    echo "--- examples before ---"
    echo "--- examples end ---"
    install -d ${D}${libdir}/
    install -d ${D}${bindir}
    install -m 0755 ${S}/bin/smartcashier ${D}${bindir}
}

FILES_${PN} += "${bindir}/*"
FILES_${PN} += "${libdir}/*"
PARALLEL_MAKEINST = ""
