function getCode() {
    //解绑清空
    $("#submit").unbind("click");
    $("#captcha").empty();
    $("#captcha").append("<p id=\"wait\" class=\"gt_show\">正在加载验证码......</p>");
    $.ajax({
        url: "/register?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            // 调用 initGeetest 初始化参数
            // 参数1：配置参数
            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
            initGeetest({
                gt: data.gt,
                challenge: data.challenge,
                new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
                offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                product: "float", // 产品形式，包括：float，popup
                width: "100%"
            }, handler);
        }
    });
}
var handler = function (captchaObj) {
    // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
    captchaObj.appendTo("#captcha");
    captchaObj.onReady(function () {
        $("#wait").hide();
    });
    $("#submit").click(function (e) {
        e.preventDefault();
        let name = $("#username").val();
        let pwd = $("#pwd").val();
        if (!checkForm(name, pwd)) {
            layer.msg("请输入用户名和密码")
            return;
        }
        var result = captchaObj.getValidate();
        if (!result) {
            $("#notice").show();
            setTimeout(function () {
                $("#notice").hide();
            }, 2000);
        } else {
            sendAjax(result);

        }
    });
};
function checkForm(...args) {
    for (let i = 0; i < args.length; i++) {
        if (args[i]==undefined||args[i].length == 0 || args[i].trim() == '') {
            return false;
        }
    }
    return true;
}
function sendAjax(result) {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: "/register",
        type: "post",
        dataType: "json",
        data: {
            "geetest_challenge": result.geetest_challenge,
            "geetest_validate": result.geetest_validate,
            "geetest_seccode": result.geetest_seccode,
            "username": $("#username").val(),
            "pwd": $("#pwd").val()
        },
        success: function (data) {
            layer.close(index);
            switch (data.code) {
                case 0:
                    layer.msg(data.msg);
                    getCode();
                    $('#myModal').modal('hide')
                    loginCheck();
                    break;
                default:
                    getCode();
                    layer.msg(data.msg);
                    break;
            }
        },
        err: function (e) {
            layer.close(index);
            console.log(e.status);
            console.log(e.statusText);
        }
    });
}
function loginCheck(){
    $.ajax({
        url: "/loginCheck?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            switch (data.code) {
                case 0:
                    $("#logInfo").text("欢迎，"+data.data);
                    //$("#userAdmin").attr("href","/u/"+data.data);
                    console.log(data.msg);
                    break;
                default:
                    console.log(data.msg);
                    break;
            }
        },
        err: function (e) {
            console.log(e.status);
            console.log(e.statusText);
        }
    });
}
function login(p){
    $.ajax({
        url: "/loginCheck?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            switch (data.code) {
                case 0:
                    console.log(data.msg);
                    location.href="/"+p+"/"+data.data;
                    break;
                default:
                    console.log(data.msg);
                    $("#myModal").modal('toggle');
                    break;
            }
        },
        err: function (e) {
            console.log(e.status);
            console.log(e.statusText);
        }
    });
}
function about(){
    console.log(1111);
    layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '400px;'
        ,shade: 0.8
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,resize: false
        ,btn: ['我知道了']
        ,btnAlign: 'c'
        ,moveType: 1 //拖拽模式，0或者1
        ,content: '<div style=" padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;"><h3>这是一个基于SpringBoot的理发店会员管理系统</h3><br>' +'<p>1.管理员查看用户信息以及查看和生成订单信息</p><br>'+ '<p>2.用户可以查看自己的用户信息以及订单信息</p>'+ '</div>'
        ,success: function(layero){
            var btn = layero.find('.layui-layer-btn');
            btn.find('.layui-layer-btn0').attr({});
        }
    });
}

function getRole(){
    $.ajax({
        url: "/role?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            switch (data.code) {
                case 0:
                    let info = data.data;
                    if (info[1]==2){
                        $('#searchDiv').css("visibility","hidden");
                    }
                    break;
                default:
                    console.log(data.msg);
                    break;
            }
        },
        err: function (e) {
            console.log(e.status);
            console.log(e.statusText);
        }
    });
}