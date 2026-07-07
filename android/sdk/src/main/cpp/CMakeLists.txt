cmake_minimum_required(VERSION 3.22.1)

project(mexamaps C CXX)

set(SRC
  # JNI headers
  ../../../../../private.h
  app/mexamaps/sdk/core/jni_helper.hpp
  app/mexamaps/sdk/core/jni_java_methods.hpp
  app/mexamaps/sdk/core/logging.hpp
  app/mexamaps/sdk/core/ScopedEnv.hpp
  app/mexamaps/sdk/core/ScopedLocalRef.hpp
  app/mexamaps/sdk/Framework.hpp
  app/mexamaps/sdk/opengl/android_gl_utils.hpp
  app/mexamaps/sdk/opengl/androidoglcontext.hpp
  app/mexamaps/sdk/opengl/androidoglcontextfactory.hpp
  app/mexamaps/sdk/opengl/gl3stub.h
  app/mexamaps/sdk/platform/GuiThread.hpp
  app/mexamaps/sdk/platform/AndroidPlatform.hpp
  app/mexamaps/sdk/util/Distance.hpp
  app/mexamaps/sdk/vulkan/android_vulkan_context_factory.hpp

  # JNI sources
  app/mexamaps/sdk/search/DisplayedCategories.cpp
  app/mexamaps/sdk/search/SearchEngine.cpp
  app/mexamaps/sdk/search/SearchRecents.cpp
  app/mexamaps/sdk/routing/roadshield/RoadShield.cpp
  app/mexamaps/sdk/routing/roadshield/RoadShield.hpp
  app/mexamaps/sdk/routing/roadshield/RoadShieldInfo.cpp
  app/mexamaps/sdk/routing/roadshield/RoadShieldInfo.hpp
  app/mexamaps/sdk/routing/roadshield/RoadShieldType.cpp
  app/mexamaps/sdk/routing/roadshield/RoadShieldType.hpp
  app/mexamaps/sdk/routing/CarDirection.cpp
  app/mexamaps/sdk/routing/CarDirection.hpp
  app/mexamaps/sdk/routing/JunctionInfo.hpp
  app/mexamaps/sdk/routing/LaneInfo.cpp
  app/mexamaps/sdk/routing/LaneInfo.hpp
  app/mexamaps/sdk/routing/PedestrianDirection.cpp
  app/mexamaps/sdk/routing/PedestrianDirection.hpp
  app/mexamaps/sdk/routing/RouteMarkData.hpp
  app/mexamaps/sdk/routing/RouteMarkType.hpp
  app/mexamaps/sdk/routing/RoutePointInfo.hpp
  app/mexamaps/sdk/routing/RouteRecommendationType.hpp
  app/mexamaps/sdk/routing/RoutingInfo.hpp
  app/mexamaps/sdk/routing/RoutingOptions.cpp
  app/mexamaps/sdk/routing/TransitRouteInfo.hpp
  app/mexamaps/sdk/routing/TransitStepInfo.hpp
  app/mexamaps/sdk/ChoosePositionMode.cpp
  app/mexamaps/sdk/MapStyle.cpp
  app/mexamaps/sdk/MexaMaps.cpp
  app/mexamaps/sdk/Router.cpp
  app/mexamaps/sdk/core/jni_helper.cpp
  app/mexamaps/sdk/core/jni_java_methods.cpp
  app/mexamaps/sdk/core/logging.cpp
  app/mexamaps/sdk/bookmarks/data/Bookmark.cpp
  app/mexamaps/sdk/bookmarks/data/Bookmark.hpp
  app/mexamaps/sdk/bookmarks/data/BookmarkCategory.cpp
  app/mexamaps/sdk/bookmarks/data/BookmarkCategory.hpp
  app/mexamaps/sdk/bookmarks/data/BookmarkManager.cpp
  app/mexamaps/sdk/bookmarks/data/ElevationInfo.cpp
  app/mexamaps/sdk/bookmarks/data/ElevationInfo.hpp
  app/mexamaps/sdk/bookmarks/data/Icon.cpp
  app/mexamaps/sdk/bookmarks/data/Icon.hpp
  app/mexamaps/sdk/bookmarks/data/MapObject.cpp
  app/mexamaps/sdk/bookmarks/data/MapObject.hpp
  app/mexamaps/sdk/bookmarks/data/Metadata.cpp
  app/mexamaps/sdk/bookmarks/data/Metadata.hpp
  app/mexamaps/sdk/bookmarks/data/PredefinedColors.cpp
  app/mexamaps/sdk/bookmarks/data/PredefinedColors.hpp
  app/mexamaps/sdk/bookmarks/data/Track.cpp
  app/mexamaps/sdk/bookmarks/data/Track.hpp
  app/mexamaps/sdk/bookmarks/data/TrackStatistics.cpp
  app/mexamaps/sdk/bookmarks/data/TrackStatistics.hpp
  app/mexamaps/sdk/DownloadResourcesLegacyActivity.cpp
  app/mexamaps/sdk/editor/Editor.cpp
  app/mexamaps/sdk/editor/OpeningHours.cpp
  app/mexamaps/sdk/editor/OsmOAuth.cpp
  app/mexamaps/sdk/Framework.cpp
  app/mexamaps/sdk/isolines/IsolinesManager.cpp
  app/mexamaps/sdk/LocationState.cpp
  app/mexamaps/sdk/Map.cpp
  app/mexamaps/sdk/MapManager.cpp
  app/mexamaps/sdk/settings/UnitLocale.cpp
  app/mexamaps/sdk/settings/MapLanguageCode.cpp
  app/mexamaps/sdk/sound/tts.cpp
  app/mexamaps/sdk/subway/SubwayManager.cpp
  app/mexamaps/sdk/TrackRecorder.cpp
  app/mexamaps/sdk/TrafficState.cpp
  app/mexamaps/sdk/opengl/android_gl_utils.cpp
  app/mexamaps/sdk/opengl/androidoglcontext.cpp
  app/mexamaps/sdk/opengl/androidoglcontextfactory.cpp
  app/mexamaps/sdk/opengl/gl3stub.cpp
  app/mexamaps/sdk/platform/GuiThread.cpp
  app/mexamaps/sdk/platform/PlatformGuiThread.cpp
  app/mexamaps/sdk/platform/Language.cpp
  app/mexamaps/sdk/platform/Localization.cpp
  app/mexamaps/sdk/platform/AndroidPlatform.cpp
  app/mexamaps/sdk/platform/PThreadImpl.cpp
  app/mexamaps/sdk/platform/SecureStorage.cpp
  app/mexamaps/sdk/platform/SocketImpl.cpp
  app/mexamaps/sdk/util/Config.cpp
  app/mexamaps/sdk/util/GeoUtils.cpp
  app/mexamaps/sdk/util/HttpClient.cpp
  app/mexamaps/sdk/util/Language.cpp
  app/mexamaps/sdk/util/LogsManager.cpp
  app/mexamaps/sdk/util/NetworkPolicy.cpp
  app/mexamaps/sdk/util/StringUtils.cpp
  app/mexamaps/sdk/util/UiThread.cpp
  app/mexamaps/sdk/vulkan/android_vulkan_context_factory.cpp
)

omim_add_library(${PROJECT_NAME} SHARED ${SRC})

target_include_directories(${PROJECT_NAME} PRIVATE .)

target_link_libraries(${PROJECT_NAME} PRIVATE
  # MexaMaps libs
  map
  # ge0
  # tracking
  # routing
  # traffic
  # routing_common
  # drape_frontend
  # shaders
  # search
  # storage
  # descriptions
  # drape
  # kml
  # editor
  # transit
  # indexer
  # platform
  # mwm_diff
  # bsdiff
  # geometry
  # coding
  # base
  # opening_hours
  # pugixml
  # expat
  # freetype
  # minizip
  # succinct
  # stb_image
  # icu
  # agg
  # vulkan_wrapper

  # Android libs
  log
  android
  EGL
  GLESv2
  atomic
)

#target_link_options(${PROJECT_NAME} PRIVATE "-fuse-ld=gold")
