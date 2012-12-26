var script = (function(){
    var src=document.getElementsByTagName('script');
    return src[src.length-1].getAttribute("src");
	})();
var q = script.indexOf('?');
if (q != -1) {
	path = script.substring(0, script.lastIndexOf("/")+1);
	filename = script.substring(path.length,q);
	files = script.substring(q+1, script.length).split("|");
	for(file in files) {
	    document.write("<script type='text/javascript'src='"+path+files[file]+".js'></script>");
		}
	}
var JSLoader = {
	load: function(){
		for (i in arguments) {
		    document.write("<script type='text/javascript'src='"+arguments[i]+".js'></script>");			
			}
		}	
	}