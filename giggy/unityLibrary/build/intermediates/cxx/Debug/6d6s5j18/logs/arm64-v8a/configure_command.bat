@echo off
"C:\\Users\\SSAFY\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HC:\\Users\\SSAFY\\Documents\\S09P22D208\\giggy\\unityLibrary\\src\\main\\cpp" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=24" ^
  "-DANDROID_PLATFORM=android-24" ^
  "-DANDROID_ABI=arm64-v8a" ^
  "-DCMAKE_ANDROID_ARCH_ABI=arm64-v8a" ^
  "-DANDROID_NDK=C:\\Program Files\\Unity\\Hub\\Editor\\2023.1.14f1\\Editor\\Data\\PlaybackEngines\\AndroidPlayer\\NDK" ^
  "-DCMAKE_ANDROID_NDK=C:\\Program Files\\Unity\\Hub\\Editor\\2023.1.14f1\\Editor\\Data\\PlaybackEngines\\AndroidPlayer\\NDK" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Program Files\\Unity\\Hub\\Editor\\2023.1.14f1\\Editor\\Data\\PlaybackEngines\\AndroidPlayer\\NDK\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\SSAFY\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=C:\\Users\\SSAFY\\Documents\\S09P22D208\\giggy\\unityLibrary\\build\\intermediates\\cxx\\Debug\\6d6s5j18\\obj\\arm64-v8a" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=C:\\Users\\SSAFY\\Documents\\S09P22D208\\giggy\\unityLibrary\\build\\intermediates\\cxx\\Debug\\6d6s5j18\\obj\\arm64-v8a" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-DCMAKE_FIND_ROOT_PATH=C:\\Users\\SSAFY\\Documents\\S09P22D208\\giggy\\unityLibrary\\.cxx\\Debug\\6d6s5j18\\prefab\\arm64-v8a\\prefab" ^
  "-BC:\\Users\\SSAFY\\Documents\\S09P22D208\\giggy\\unityLibrary\\.cxx\\Debug\\6d6s5j18\\arm64-v8a" ^
  -GNinja ^
  "-DBUILD_GRADLE_DIRECTORY=C:\\Users\\SSAFY\\Documents\\S09P22D208\\giggy\\unityLibrary" ^
  "-DANDROID_STL=c++_shared"
