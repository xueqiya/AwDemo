# AwDemo
基于Chromium AwContents的简单浏览器demo

```
cd ~
git clone https://chromium.googlesource.com/chromium/tools/depot_tools.git
export PATH="$PATH:$HOME/depot_tools"

mkdir ~/chromium && cd ~/chromium
fetch --nohooks android
cd src
gclient sync

git checkout tags/92.0.4515.115
gclient sync

build/install-build-deps-android.sh
gclient runhooks

gn args out/arm

target_os="android"
target_cpu="arm"
is_debug=false
cc_wrapper="ccache"
symbol_level=0
blink_symbol_level=0
enable_nacl=false
use_debug_fission=false
clang_use_chrome_plugins=false
proprietary_codecs=true
ffmpeg_branding="Chrome"

ninja -C out/arm webview_instrumentation_apk

gn args out/arm64

target_os="android"
target_cpu="arm64"
is_debug=false
cc_wrapper="ccache"
symbol_level=0
blink_symbol_level=0
enable_nacl=false
use_debug_fission=false
clang_use_chrome_plugins=false
proprietary_codecs=true
ffmpeg_branding="Chrome"

ninja -C out/arm64 webview_instrumentation_apk
# It takes about 1.5 hours. (Intel i7-10700k, RAM 16GB, ubuntu, arm/arm64 target)
```