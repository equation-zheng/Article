var moveLeft = $('.banner .moveLeft').eq(0);
	var moveRight = $('.banner .moveRight').eq(0);
	
	var index = 0;
	var imgwidth = $('.banner ul li').width()+95;
	var len = $('.banner ul li').length - 2;
	//console.log(imgwidth * len+" | "+imgwidth*(len+2));
	
	function movePicture() {
		$('.banner ul').animate({marginLeft : -imgwidth * (index + 1)}, 1000,
		function() {
			if (index == len) {
				$(this).css('margin-left', -imgwidth);
				index = 0;
			} 
			if (index == -1){
				$(this).css('margin-left', -imgwidth * len);
				index = len -1;
			}
		})
	}
	var timer = null;

	moveLeft.on('click', function() {
		clearTimeout(timer);
		timer = setTimeout(function(){
			index--;
			movePicture();
		}, 500)
	})
	moveRight.on('click', function() {
		clearTimeout(timer);
		timer = setTimeout(function(){
			index++;
			movePicture();
		}, 500)
	})