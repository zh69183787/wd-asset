//监测浏览器IE版本or浏览器内核
var _IE = (function(){
   var v = 3, div = document.createElement('div'), all = div.getElementsByTagName('i');
   while (
       div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->',
       all[0]
   );
   return v > 4 ? v : false ;
}());