
var indexTitle = $("#indexTitle").eq(0);
indexTitle.css("background", "#74b0e2");
	

/*轮播代码*/
var leftBtn = $(".leftBtn").eq(0);
var rightBtn = $(".rightBtn").eq(0);
var time = null;

rightBtn.on("click", function() {
    clearTimeout(time);
    time = setTimeout(function() {
        index++;
        movePicture();
        automove();
    },500);
});

leftBtn.on("click", function() {
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
    });
}
window.onload(automove());

function automove() {
    time = setTimeout(function() {
    	movePicture(index++);
    	automove();
    },5000);
}

/*轮播结束*/

//打开详情页
function detail(id) {
	var a = document.createElement("a");
	a.href = "detail.jsp?id=" + id;
	//console.log(a);
	a.target = "_new";	//	知道在新窗口打开
	a.click();	//触发打开事件
}
















