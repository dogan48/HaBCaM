var manifestUri = 'http://192.168.1.24:81/dataset/car-20120827-manifest.mpd';



function initApp() {
  shaka.polyfill.installAll();
  shaka.Player.support().then(function(support) {
    if (support.supported) {
      initPlayer();
    } else {
      console.error('Browser not supported!');
    }
  });
}

function initPlayer() {
  var video = document.getElementById('video');
  var player = new shaka.Player(video);
  window.player = player;
  player.addEventListener('error', onErrorEvent);
  player.load(manifestUri).then(function() {
    console.log('The video has now been loaded!');
   	var track = player.getTracks()[0]
		player.selectTrack(track)
    
  }).catch(onError);  // onError is executed if the asynchronous load fails.
}

function onErrorEvent(event) {
  onError(event.detail);
}

function onError(error) {
  console.error('Error code', error.code, 'object', error);
}