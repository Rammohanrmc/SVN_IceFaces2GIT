if(!window.mobi){window.mobi={};}if(!window.ice){window.ice={};}if(!window.ice.mobi){window.ice.mobi={};}ice.mobi.splitpane={panels:{},initClient:function(a,b){if(!this.panels[a]){this.panels[a]=ice.mobi.splitpane.Scrollable(a,b);this.panels[a].resize(a);}else{this.panels[a].resize(a);}},resizeHt:function(a){if(this.panels[a]){this.panels[a].resize(a);}else{this.panels[a].unload(a);}},Scrollable:function Scrollable(a,g){var c=a+"_wrp";var f=document.getElementById(a+"_left");var b=document.getElementById(a+"_right");var e=function(){ice.mobi.splitpane.resizeHt(a);};if(g.width){var d=g.width||-1;if(d>0&&d<99){f.style.width=d+"%";b.style.width=(100-d)+"%";}}ice.mobi.addListener(window,"resize",e);return{resize:function(j){var i=0;var l=document.getElementById(j+"_left");var k=document.getElementById(j+"_right");var o=document.getElementById(j+"_splt");var h=window.document.body||null;if(h==null){return;}if(l&&k){if(window.innerHeight){i=window.innerHeight;}else{if(h.parentElement.clientHeight){i=h.parentElement.clientHeight;}else{if(h){if(h.clientHeight){i=h.clientHeight;}}}}if(i>0){var m=i-l.offsetTop;var n=i-k.offsetTop;if(m>0){l.style.height=""+m+"px";}if(n>0){k.style.height=""+n+"px";}}}},unload:function(){ice.mobi.removeListener(window,"resize",e);}};}};