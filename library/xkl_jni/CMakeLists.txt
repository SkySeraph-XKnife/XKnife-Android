# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)  # 可被执行的 CMake 最低版本( Android Native > 3.4.0)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds it for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library. 库名（唯一）
             native-lib

             # Sets the library as a shared library. 加载方式，SHARED or STATIC
             SHARED

             # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.
             src/main/cpp/native-lib.cpp
             src/main/cpp/encrypt.cpp
             src/main/cpp/utils/convert.cpp)
# add_library (<name> [STATIC | SHARED | MODULE] source1 [source2 ...])

# Specifies a path to native header files.
include_directories(src/main/cpp/ src/main/cpp/utils/)
# include_directories (dir1/ [dir2/ ...])   指定头文件的目录。用于告知 CMake 在编译期寻找合适的头文件。


# Searches for a specified prebuilt library and stores the path as a
# variable. Because system libraries are included in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

# 将由 NDK API 预先构建的库的路径以变量形式存储，为后面链接构建库提供预备参数。
find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in the
# build script, prebuilt third-party libraries, or system libraries.


# 构建的库中链接其他库
target_link_libraries( # Specifies the target library.
                       native-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
