//var leftBtn = $(".leftBtn").eq(0);
//var rightBtn = $(".rightBtn").eq(0);
//var time = null;
//
//rightBtn.on('click', function() {
//    clearTimeout(time);
//    time = setTimeout(function() {
//        index++;
//        movePicture();
//        automove();
//    },500);
//});
//
//leftBtn.on('click', function() {
//    clearTimeout(time);
//    time = setTimeout(function() {
//        index--;
//        movePicture();
//        automove();
//    },500);
//});
//
//
//
//var ul = $('.banner .content ul').eq(0);
//var index = 0;
//var imgwidth = $('.banner .content ul li').width();
//var len = $('.banner .content ul li').length - 1;
//
//function movePicture() {
//    $('.banner .content ul').animate({'margin-left': -imgwidth * (index+1)},1000,function() {
//        if (index == len) {
//            $(this).css('margin-left', - imgwidth);
//            index = 0;
//        }
//        if (index == -1) {
//            $(this).css('margin-left', -imgwidth * len);
//            index = len - 1;
//        }
//    })
//}
//window.onload(automove());
//
//function automove() {
//    time = setTimeout(function() {
//    	movePicture(index++);
//    	automove();
//    },6000);
//}
//---------------------------------------------------
var    $ct = $('.banner .content ul'),      //获取视口节点
$items = $ct.children(), //获取视口下需要轮播的图片元素
$pre = $('.leftBtn'),       //获取上一张功能按钮
$next = $('.rightBtn'),     //获取下一张功能按钮



imgCount=$items.size(),//第三行重新计算元素的数量，原本是4个。现在变成了6个（这里要注意！
						//重新计算数量要重新查找DOM元素，不能直接利用上面的$items.size来计算，
						//要重新使用$ct.children().size），不然还是4张！）


imgWidth = $items.width();//获取滚动元素的宽


$ct.append($items.first().clone());//第一行首先获取到轮播图片元素的最后一张然后clone，然后将clone后的副本prepend到视口的第一个
$ct.prepend($items.last().clone());//第二行同理，把第一张clone一下append到最后面



var	newCount=$ct.children().size();

//第四行设置视口的初始位置和它的总宽度然后元素的排列就变成了如下视口图（自己画的有点丑）
$ct.css({left:0-imgWidth,width:newCount*imgWidth});
$next.on('click',function(){
	// if(lock===false){
		// lock=true;
		playnext();
	// }
	
})
$pre.on('click',function(){
	// if(lock===false){
	// 	lock=true;
		playpre();
	// }
	
})
var curidx =0;
var mov = 1;
var lock=false;
function playnext(){
	$ct.animate({left:'-='+mov*imgWidth},function(){
		curidx+=mov;
		if(curidx===imgCount){
			$ct.css({left:0-imgWidth});
			curidx=0;
		}
		// lock =false;
	})
}

function playpre(){
	$ct.animate({left:'+='+mov*imgWidth},function(){
		curidx-=mov;
		if(curidx===(-1)){
			$ct.css({left:0-imgWidth*imgCount});
			curidx=3;
		}
		// lock =false;
	})
}	
