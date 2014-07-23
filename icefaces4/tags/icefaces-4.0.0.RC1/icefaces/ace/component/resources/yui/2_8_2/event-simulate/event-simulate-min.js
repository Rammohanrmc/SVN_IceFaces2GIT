/*
Copyright (c) 2010, Yahoo! Inc. All rights reserved.
Code licensed under the BSD License:
http://developer.yahoo.com/yui/license.html
version: 2.8.2r1
*/
YAHOO.util.UserAction={simulateKeyEvent:function(F,J,E,C,L,B,A,K,H,N,M){F=YAHOO.util.Dom.get(F);if(!F){throw new Error("simulateKeyEvent(): Invalid target.");}if(YAHOO.lang.isString(J)){J=J.toLowerCase();switch(J){case"keyup":case"keydown":case"keypress":break;case"textevent":J="keypress";break;default:throw new Error("simulateKeyEvent(): Event type '"+J+"' not supported.");}}else{throw new Error("simulateKeyEvent(): Event type must be a string.");}if(!YAHOO.lang.isBoolean(E)){E=true;}if(!YAHOO.lang.isBoolean(C)){C=true;}if(!YAHOO.lang.isObject(L)){L=window;}if(!YAHOO.lang.isBoolean(B)){B=false;}if(!YAHOO.lang.isBoolean(A)){A=false;}if(!YAHOO.lang.isBoolean(K)){K=false;}if(!YAHOO.lang.isBoolean(H)){H=false;}if(!YAHOO.lang.isNumber(N)){N=0;}if(!YAHOO.lang.isNumber(M)){M=0;}var I=null;if(YAHOO.lang.isFunction(document.createEvent)){try{I=document.createEvent("KeyEvents");I.initKeyEvent(J,E,C,L,B,A,K,H,N,M);}catch(G){try{I=document.createEvent("Events");}catch(D){I=document.createEvent("UIEvents");}finally{I.initEvent(J,E,C);I.view=L;I.altKey=A;I.ctrlKey=B;I.shiftKey=K;I.metaKey=H;I.keyCode=N;I.charCode=M;}}F.dispatchEvent(I);}else{if(YAHOO.lang.isObject(document.createEventObject)){I=document.createEventObject();I.bubbles=E;I.cancelable=C;I.view=L;I.ctrlKey=B;I.altKey=A;I.shiftKey=K;I.metaKey=H;I.keyCode=(M>0)?M:N;F.fireEvent("on"+J,I);}else{throw new Error("simulateKeyEvent(): No event simulation framework present.");}}},simulateMouseEvent:function(K,P,H,E,Q,J,G,F,D,B,C,A,O,M,I,L){K=YAHOO.util.Dom.get(K);if(!K){throw new Error("simulateMouseEvent(): Invalid target.");}if(YAHOO.lang.isString(P)){P=P.toLowerCase();switch(P){case"mouseover":case"mouseout":case"mousedown":case"mouseup":case"click":case"dblclick":case"mousemove":break;default:throw new Error("simulateMouseEvent(): Event type '"+P+"' not supported.");}}else{throw new Error("simulateMouseEvent(): Event type must be a string.");}if(!YAHOO.lang.isBoolean(H)){H=true;}if(!YAHOO.lang.isBoolean(E)){E=(P!="mousemove");}if(!YAHOO.lang.isObject(Q)){Q=window;}if(!YAHOO.lang.isNumber(J)){J=1;}if(!YAHOO.lang.isNumber(G)){G=0;}if(!YAHOO.lang.isNumber(F)){F=0;}if(!YAHOO.lang.isNumber(D)){D=0;}if(!YAHOO.lang.isNumber(B)){B=0;}if(!YAHOO.lang.isBoolean(C)){C=false;}if(!YAHOO.lang.isBoolean(A)){A=false;}if(!YAHOO.lang.isBoolean(O)){O=false;}if(!YAHOO.lang.isBoolean(M)){M=false;}if(!YAHOO.lang.isNumber(I)){I=0;}var N=null;if(YAHOO.lang.isFunction(document.createEvent)){N=document.createEvent("MouseEvents");if(N.initMouseEvent){N.initMouseEvent(P,H,E,Q,J,G,F,D,B,C,A,O,M,I,L);}else{N=document.createEvent("UIEvents");N.initEvent(P,H,E);N.view=Q;N.detail=J;N.screenX=G;N.screenY=F;N.clientX=D;N.clientY=B;N.ctrlKey=C;N.altKey=A;N.metaKey=M;N.shiftKey=O;N.button=I;N.relatedTarget=L;}if(L&&!N.relatedTarget){if(P=="mouseout"){N.toElement=L;}else{if(P=="mouseover"){N.fromElement=L;}}}K.dispatchEvent(N);}else{if(YAHOO.lang.isObject(document.createEventObject)){N=document.createEventObject();N.bubbles=H;N.cancelable=E;N.view=Q;N.detail=J;N.screenX=G;N.screenY=F;N.clientX=D;N.clientY=B;N.ctrlKey=C;N.altKey=A;N.metaKey=M;N.shiftKey=O;switch(I){case 0:N.button=1;break;case 1:N.button=4;break;case 2:break;default:N.button=0;}N.relatedTarget=L;K.fireEvent("on"+P,N);}else{throw new Error("simulateMouseEvent(): No event simulation framework present.");}}},fireMouseEvent:function(C,B,A){A=A||{};this.simulateMouseEvent(C,B,A.bubbles,A.cancelable,A.view,A.detail,A.screenX,A.screenY,A.clientX,A.clientY,A.ctrlKey,A.altKey,A.shiftKey,A.metaKey,A.button,A.relatedTarget);},click:function(B,A){this.fireMouseEvent(B,"click",A);},dblclick:function(B,A){this.fireMouseEvent(B,"dblclick",A);},mousedown:function(B,A){this.fireMouseEvent(B,"mousedown",A);},mousemove:function(B,A){this.fireMouseEvent(B,"mousemove",A);},mouseout:function(B,A){this.fireMouseEvent(B,"mouseout",A);},mouseover:function(B,A){this.fireMouseEvent(B,"mouseover",A);},mouseup:function(B,A){this.fireMouseEvent(B,"mouseup",A);},fireKeyEvent:function(B,C,A){A=A||{};this.simulateKeyEvent(C,B,A.bubbles,A.cancelable,A.view,A.ctrlKey,A.altKey,A.shiftKey,A.metaKey,A.keyCode,A.charCode);},keydown:function(B,A){this.fireKeyEvent("keydown",B,A);},keypress:function(B,A){this.fireKeyEvent("keypress",B,A);},keyup:function(B,A){this.fireKeyEvent("keyup",B,A);}};YAHOO.register("event-simulate",YAHOO.util.UserAction,{version:"2.8.2r1",build:"7"});