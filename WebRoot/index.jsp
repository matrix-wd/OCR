<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>打开电脑摄像头</title>
	<style>
    	.container {
    		width: 400px;
    		margin: 0 auto;
    	}
    	#video {
    		width: 400px;
    		height: 250px;
    		border: 1px solid gray;
    	}
    	.btn {
    		width: 140px;
    		height: 60px;
    		background: orange;
    		border: none;
    		margin-left: 40px;
    		margin-bottom: 10px;
    		margin-top: 10px;
    		color: white;
    		font-size: 16px;
    		cursor: pointer;
    	}
    	#canvas {
    		width: 400px;
    		height: 250px;
    		border: 1px solid red;
    	}
    </style>
</head>
<body>
	<div class="container">
    	<video id="video" autoplay></video><br/>
    	<button class="btn" id="open">打开摄像头</button>
    	<button class="btn" id="take">拍照</button>
    	<canvas id="canvas" width="640" height="480"></canvas>
    </div>
    <script>
    	var Open = document.getElementById("open");
    	var oVideo = document.getElementById("video");
    	var oTake = document.getElementById("take");
    	var oCanvas = document.getElementById('canvas');  
        var context = oCanvas.getContext('2d'); 

    	navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;  
        window.URL = window.URL || window.webkitURL || window.mozURL || window.msURL;


        var exArray = []; //存储设备源ID  
	      
    	// 开启按钮
    	Open.onclick = function() {
    		if (navigator.getUserMedia) {  
                navigator.getUserMedia({  
                    'video': {  
                        'optional': [{  
                            'sourceId': exArray[1] 
                        }]  
                    },
                }, successFunc, errorFunc); 
            }  
            else {  
                alert('不支持摄像头');
            }  
    	};

    	// 拍照按钮
    	oTake.onclick = function() {
    		context.drawImage(oVideo, 0, 0, 640, 480);
    		var req = new XMLHttpRequest();
			var base64 = oCanvas.toDataURL();
			console.log(base64);
			var _base64 = base64.substring(base64.indexOf("base64")+7, base64.length);
			var data = "action=postPicture&base64="+_base64;
			// 我们提交到server.php这个页面进行处理  
			req.open("POST","servlet",true);
			req.onreadystatechange = function(){  
			    if((req.readyState == 4)&&(req.status==200)){  
			        var result = req.responseText;
			        if(result == 0) alert("提交失败");
			        else alert(result);
			    }
			};
			req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			req.send(data);
    	};

    	// 开启失败
    	function errorFunc(e) {  
            alert('Error！'+e);  
        }

        // 开启成功
        function successFunc(stream) {
            if (oVideo.mozSrcObject !== undefined) {  
                oVideo.mozSrcObject = stream;  
            }
            else {
                oVideo.src = window.URL && window.URL.createObjectURL(stream) || stream;  
            }
        }
    </script>
</body>
</html>