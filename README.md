# app-de-capture-de-video
Application android qui utilise la camera pour sauvegarder des videos et des photos


* https://developer.android.com/guide/topics/media/exoplayer/rtsp
* https://github.com/VideoExpertsGroup/RTSP.Server.Android/tree/master
* https://github.com/fyhertz/libstreaming (pas mis a jour depuis 5 ans)
* https://github.com/alexeyvasilyev/rtsp-client-android plus recent et sans lag
* https://github.com/bluenviron/mediamtx Serveur qui fait tout

Commandes utiles
---

* `ffmpeg -hide_banner -list_devices true -f avfoundation -i dummy` sur mac pour trouver sa webcam
* `ffmpeg -hide_banner -f avfoundation -framerate 30 -i "0" -pix_fmt yuv420p -c:v libx264 -preset ultrafast -b:v 600k -f rtsp rtsp://localhost:8554/cam` 
Pour streamer. Il faut utiliser le serveur local, avec docker il faut surement remplacer localhost.
* `vlc --network-caching=50 rtsp://localhost:8554/mystream` pour voir le stream 

Cela semble marcher https://github.com/ar-android/libstreaming 