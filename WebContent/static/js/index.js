var leftBtn = $(".leftBtn").eq(0);
var rightBtn = $(".rightBtn").eq(0);
var time = null;

rightBtn.on('click', function() {
    clearTimeout(time);
    time = setTimeout(function() {
        index++;
        movePicture();
        automove();
    },500);
});

leftBtn.on('click', function() {
    clearTimeout(time);
    time = setTimeout(function() {
        index--;
        movePicture();
        automove();
    },500);
});



var ul = $('.banner .content ul').eq(0);
var index = 0;
var imgwidth = $('.banner .content ul li').width();
var len = $('.banner .content ul li').length - 1;

function movePicture() {
    $('.banner .content ul').animate({'margin-left': -imgwidth * (index+1)},1000,function() {
        if (index == len) {
            $(this).css('margin-left', - imgwidth);
            index = 0;
        }
        if (index == -1) {
            $(this).css('margin-left', -imgwidth * len);
            index = len - 1;
        }
    })
}
window.onload(automove());

function automove() {
    time = setTimeout(function() {
    	movePicture(index++);
    	automove();
    },6000);
}

