
var indexTitle = $("#indexTitle").eq(0);
indexTitle.css("background", "#74b0e2");
	window.onload = function(){
    $("#box").hover(function(){
        $(".btn").fadeIn();
    },function(){
        $(".btn").fadeOut()
    })
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
        timer = setInterval(func,2000)
    })
    li.hover(function(){
        clearInterval(timer);
        change($(this).index())
    },function(){
        index = $(this).index();
        timer = setInterval(func,2000)
    });
    $(".leftBtn").click(function(){
        index--;
        if(index < 0){
            index = 7
        }
        change(index);
    })
    $(".rightBtn").click(function(){
        index++;
        if(index > 7){
            index = 0;
        }
        change(index);
    })
}


//打开详情页
function detail(id) {
	var a = document.createElement("a");
	a.href = "detail.jsp?id=" + id;
	//console.log(a);
	a.target = "_new";	//	知道在新窗口打开
	a.click();	//触发打开事件
}
















