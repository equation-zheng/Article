$("#indexTitle").css("background", "#74b0e2");
window.onload = function(){
    $("#box").hover(function(){
        $(".btn").fadeIn();
    },function(){
        $(".btn").fadeOut();
    });
    var li = $("#box ul li");
    //console.log($li);
    var timer = setInterval(func,2000);
    var index = 0;
    function func(){
        if(index < 7){
            index++;
        }
        else{
            index=0;
        }
        change(index);
    }
    function change(n){
        li.eq(n).fadeIn("400").siblings().fadeOut("400");
        $(".spiner p").eq(n).css("border-color","#969087").siblings().css("border-color","#B4ADA2");
        $(".spiner p").eq(n).css("background","rgba(0,0,0,0)").siblings().css("background","rgba(0,0,0,0.5)");
    }
    change(index);
    $(".spiner p").hover(function(){
        clearInterval(timer);
        var temp = $(this).index();
        change(temp);
    },function(){
        var temp = $(this).index();
        index = temp;
        timer = setInterval(func,2000);
    });
    li.hover(function(){
        clearInterval(timer);
        change($(this).index());
    },function(){
        index = $(this).index();
        timer = setInterval(func,2000);
    });
    $(".leftBtn").click(function(){
        index--;
        if(index < 0){
            index = 7;
        }
        change(index);
    });
    $(".rightBtn").click(function(){
        index++;
        if(index > 7){
            index = 0;
        }
        change(index);
    });
    
    var myDate = new Date();
    var h = myDate.getHours();
    if(h >= 6 && h <= 11) {
    	$(".category .items .item_banner")
    	.css("background", "#AB907E");
    	$(".category .items .item")
    	.css("background", "#CDD1B8");
    	
    }else if(h >= 12 && h <= 15) {
    	$(".category .items .item_banner")
    	.css("background", "#728A90");
    	$(".category .items .item")
    	.css("background", "#ADBCB9");

    }else if(h >= 16 && h <= 18) {
    	$(".category .items .item_banner")
    	.css("background", "#595769");
    	$(".category .items .item")
    	.css("background", "#DBC091");

    }else{
    	$(".category .items .item_banner")
    	.css("background", "#371E1A");
    	$(".category .items .item")
    	.css("background", "#A46851");
    }
};


//打开详情页
function detail(id) {
	var a = document.createElement("a");
	a.href = "detail.jsp?id=" + id;
	//console.log(a);
	a.target = "_new";	//	知道在新窗口打开
	a.click();	//触发打开事件
}
//点击查看该类所有文章列表
var item = document.getElementsByClassName("items");
var btn = true;
function itemAll(key) {
	if (btn) {
		item[key].style.height = "auto";
		btn = false;
	} else {
		item[key].style.height = "273px";
		btn = true;
	}
}

















