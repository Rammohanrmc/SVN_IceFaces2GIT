﻿/*
Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

(function(){var a=CKEDITOR.tools.cssLength,b=function(f){var g=this.id;if(!f.info)f.info={};f.info[g]=this.getValue();};function c(f){var g=0,h=0;for(var i=0,j,k=f.$.rows.length;i<k;i++){j=f.$.rows[i],g=0;for(var l=0,m,n=j.cells.length;l<n;l++){m=j.cells[l];g+=m.colSpan;}g>h&&(h=g);}return h;};function d(f){return function(){var g=this.getValue(),h=!!(CKEDITOR.dialog.validate.integer()(g)&&g>0);if(!h){alert(f);this.select();}return h;};};function e(f,g){var h=function(j){return new CKEDITOR.dom.element(j,f.document);},i=f.plugins.dialogadvtab;return{title:f.lang.table.title,minWidth:310,minHeight:CKEDITOR.env.ie?310:280,onLoad:function(){var j=this,k=j.getContentElement('advanced','advStyles');if(k)k.on('change',function(l){var m=this.getStyle('width',''),n=j.getContentElement('info','txtWidth');n&&n.setValue(m,true);var o=this.getStyle('height',''),p=j.getContentElement('info','txtHeight');p&&p.setValue(o,true);});},onShow:function(){var r=this;var j=f.getSelection(),k=j.getRanges(),l=null,m=r.getContentElement('info','txtRows'),n=r.getContentElement('info','txtCols'),o=r.getContentElement('info','txtWidth'),p=r.getContentElement('info','txtHeight');if(g=='tableProperties'){if(l=j.getSelectedElement())l=l.getAscendant('table',true);else if(k.length>0){if(CKEDITOR.env.webkit)k[0].shrink(CKEDITOR.NODE_ELEMENT);var q=k[0].getCommonAncestor(true);l=q.getAscendant('table',true);}r._.selectedElement=l;}if(l){r.setupContent(l);m&&m.disable();n&&n.disable();}else{m&&m.enable();n&&n.enable();}o&&o.onChange();p&&p.onChange();},onOk:function(){var j=f.getSelection(),k=this._.selectedElement&&j.createBookmarks(),l=this._.selectedElement||h('table'),m=this,n={};this.commitContent(n,l);if(n.info){var o=n.info;if(!this._.selectedElement){var p=l.append(h('tbody')),q=parseInt(o.txtRows,10)||0,r=parseInt(o.txtCols,10)||0;for(var s=0;s<q;s++){var t=p.append(h('tr'));for(var u=0;u<r;u++){var v=t.append(h('td'));if(!CKEDITOR.env.ie)v.append(h('br'));}}}var w=o.selHeaders;if(!l.$.tHead&&(w=='row'||w=='both')){var x=new CKEDITOR.dom.element(l.$.createTHead());p=l.getElementsByTag('tbody').getItem(0);var y=p.getElementsByTag('tr').getItem(0);for(s=0;s<y.getChildCount();s++){var z=y.getChild(s);if(z.type==CKEDITOR.NODE_ELEMENT&&!z.data('cke-bookmark')){z.renameNode('th');z.setAttribute('scope','col');}}x.append(y.remove());}if(l.$.tHead!==null&&!(w=='row'||w=='both')){x=new CKEDITOR.dom.element(l.$.tHead);p=l.getElementsByTag('tbody').getItem(0);var A=p.getFirst();while(x.getChildCount()>0){y=x.getFirst();
for(s=0;s<y.getChildCount();s++){var B=y.getChild(s);if(B.type==CKEDITOR.NODE_ELEMENT){B.renameNode('td');B.removeAttribute('scope');}}y.insertBefore(A);}x.remove();}if(!this.hasColumnHeaders&&(w=='col'||w=='both'))for(t=0;t<l.$.rows.length;t++){B=new CKEDITOR.dom.element(l.$.rows[t].cells[0]);B.renameNode('th');B.setAttribute('scope','row');}if(this.hasColumnHeaders&&!(w=='col'||w=='both'))for(s=0;s<l.$.rows.length;s++){t=new CKEDITOR.dom.element(l.$.rows[s]);if(t.getParent().getName()=='tbody'){B=new CKEDITOR.dom.element(t.$.cells[0]);B.renameNode('td');B.removeAttribute('scope');}}o.txtHeight?l.setStyle('height',o.txtHeight):l.removeStyle('height');o.txtWidth?l.setStyle('width',o.txtWidth):l.removeStyle('width');if(!l.getAttribute('style'))l.removeAttribute('style');}if(!this._.selectedElement){f.insertElement(l);setTimeout(function(){var C=new CKEDITOR.dom.element(l.$.rows[0].cells[0]),D=new CKEDITOR.dom.range(f.document);D.moveToPosition(C,CKEDITOR.POSITION_AFTER_START);D.select(1);},0);}else try{j.selectBookmarks(k);}catch(C){}},contents:[{id:'info',label:f.lang.table.title,elements:[{type:'hbox',widths:[null,null],styles:['vertical-align:top'],children:[{type:'vbox',padding:0,children:[{type:'text',id:'txtRows','default':3,label:f.lang.table.rows,required:true,controlStyle:'width:5em',validate:d(f.lang.table.invalidRows),setup:function(j){this.setValue(j.$.rows.length);},commit:b},{type:'text',id:'txtCols','default':2,label:f.lang.table.columns,required:true,controlStyle:'width:5em',validate:d(f.lang.table.invalidCols),setup:function(j){this.setValue(c(j));},commit:b},{type:'html',html:'&nbsp;'},{type:'select',id:'selHeaders','default':'',label:f.lang.table.headers,items:[[f.lang.table.headersNone,''],[f.lang.table.headersRow,'row'],[f.lang.table.headersColumn,'col'],[f.lang.table.headersBoth,'both']],setup:function(j){var k=this.getDialog();k.hasColumnHeaders=true;for(var l=0;l<j.$.rows.length;l++){var m=j.$.rows[l].cells[0];if(m&&m.nodeName.toLowerCase()!='th'){k.hasColumnHeaders=false;break;}}if(j.$.tHead!==null)this.setValue(k.hasColumnHeaders?'both':'row');else this.setValue(k.hasColumnHeaders?'col':'');},commit:b},{type:'text',id:'txtBorder','default':1,label:f.lang.table.border,controlStyle:'width:3em',validate:CKEDITOR.dialog.validate.number(f.lang.table.invalidBorder),setup:function(j){this.setValue(j.getAttribute('border')||'');},commit:function(j,k){if(this.getValue())k.setAttribute('border',this.getValue());else k.removeAttribute('border');
}},{id:'cmbAlign',type:'select','default':'',label:f.lang.common.align,items:[[f.lang.common.notSet,''],[f.lang.common.alignLeft,'left'],[f.lang.common.alignCenter,'center'],[f.lang.common.alignRight,'right']],setup:function(j){this.setValue(j.getAttribute('align')||'');},commit:function(j,k){if(this.getValue())k.setAttribute('align',this.getValue());else k.removeAttribute('align');}}]},{type:'vbox',padding:0,children:[{type:'hbox',widths:['5em'],children:[{type:'text',id:'txtWidth',controlStyle:'width:5em',label:f.lang.common.width,title:f.lang.common.cssLengthTooltip,'default':500,getValue:a,validate:CKEDITOR.dialog.validate.cssLength(f.lang.common.invalidCssLength.replace('%1',f.lang.common.width)),onChange:function(){var j=this.getDialog().getContentElement('advanced','advStyles');j&&j.updateStyle('width',this.getValue());},setup:function(j){var k=j.getStyle('width');k&&this.setValue(k);},commit:b}]},{type:'hbox',widths:['5em'],children:[{type:'text',id:'txtHeight',controlStyle:'width:5em',label:f.lang.common.height,title:f.lang.common.cssLengthTooltip,'default':'',getValue:a,validate:CKEDITOR.dialog.validate.cssLength(f.lang.common.invalidCssLength.replace('%1',f.lang.common.height)),onChange:function(){var j=this.getDialog().getContentElement('advanced','advStyles');j&&j.updateStyle('height',this.getValue());},setup:function(j){var k=j.getStyle('height');k&&this.setValue(k);},commit:b}]},{type:'html',html:'&nbsp;'},{type:'text',id:'txtCellSpace',controlStyle:'width:3em',label:f.lang.table.cellSpace,'default':1,validate:CKEDITOR.dialog.validate.number(f.lang.table.invalidCellSpacing),setup:function(j){this.setValue(j.getAttribute('cellSpacing')||'');},commit:function(j,k){if(this.getValue())k.setAttribute('cellSpacing',this.getValue());else k.removeAttribute('cellSpacing');}},{type:'text',id:'txtCellPad',controlStyle:'width:3em',label:f.lang.table.cellPad,'default':1,validate:CKEDITOR.dialog.validate.number(f.lang.table.invalidCellPadding),setup:function(j){this.setValue(j.getAttribute('cellPadding')||'');},commit:function(j,k){if(this.getValue())k.setAttribute('cellPadding',this.getValue());else k.removeAttribute('cellPadding');}}]}]},{type:'html',align:'right',html:''},{type:'vbox',padding:0,children:[{type:'text',id:'txtCaption',label:f.lang.table.caption,setup:function(j){var n=this;n.enable();var k=j.getElementsByTag('caption');if(k.count()>0){var l=k.getItem(0),m=l.getFirst(CKEDITOR.dom.walker.nodeType(CKEDITOR.NODE_ELEMENT));if(m&&!m.equals(l.getBogus())){n.disable();
n.setValue(l.getText());return;}l=CKEDITOR.tools.trim(l.getText());n.setValue(l);}},commit:function(j,k){if(!this.isEnabled())return;var l=this.getValue(),m=k.getElementsByTag('caption');if(l){if(m.count()>0){m=m.getItem(0);m.setHtml('');}else{m=new CKEDITOR.dom.element('caption',f.document);if(k.getChildCount())m.insertBefore(k.getFirst());else m.appendTo(k);}m.append(new CKEDITOR.dom.text(l,f.document));}else if(m.count()>0)for(var n=m.count()-1;n>=0;n--)m.getItem(n).remove();}},{type:'text',id:'txtSummary',label:f.lang.table.summary,setup:function(j){this.setValue(j.getAttribute('summary')||'');},commit:function(j,k){if(this.getValue())k.setAttribute('summary',this.getValue());else k.removeAttribute('summary');}}]}]},i&&i.createAdvancedTab(f)]};};CKEDITOR.dialog.add('table',function(f){return e(f,'table');});CKEDITOR.dialog.add('tableProperties',function(f){return e(f,'tableProperties');});})();
