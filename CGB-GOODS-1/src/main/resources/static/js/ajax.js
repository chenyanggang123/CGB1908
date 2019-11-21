function ajaxGet(url,callback){
		//debugger//js中的断点（需要打开浏览器控制台）
		//页面加载完成向服务器再次发起请求，获取服务端数
		//1.构建XHR对象
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange=function(){//callback
			//readyState==4表示通讯结束
			//status==20表示服务器响应OK
			if(xhr.readyState==4&&xhr.status==200){
				//responseText表示服务端响应的结果
				//console.log(xhr.responseText);//json格式字符串
				//将服务器端响应的json格式字符串，转换成json格式字符串
				var result = JSON.parse(xhr.responseText);
				//doHandleResponseResult(xhr.responseText);
				callback(result);
			}
		}
		//2.建立连接
		var url="doFindObjects"
		xhr.open("GET",url,true);//true代表异步
		//3.发送请求
		xhr.send(null);//get请求send、方法内部不传参数
		}