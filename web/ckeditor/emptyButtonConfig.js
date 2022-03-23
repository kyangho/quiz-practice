/**
 * @license Copyright (c) 2003-2022, CKSource Holding sp. z o.o. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function (config) {
    config.removeButtons = 'Source,Save,NewPage,Templates,ExportPdf,Preview,Print,PasteFromWord,PasteText,Paste,Copy,Cut,Undo,Redo,Replace,Find,SelectAll,Scayt,Form,Bold,Italic,Checkbox,CopyFormatting,RemoveFormat,Underline,Radio,TextField,Strike,Textarea,Subscript,Select,Superscript,Button,ImageButton,HiddenField,NumberedList,BulletedList,Indent,Outdent,Blockquote,CreateDiv,JustifyRight,JustifyBlock,BidiLtr,BidiRtl,JustifyCenter,JustifyLeft,Language,Link,Unlink,Anchor,Table,Image,HorizontalRule,SpecialChar,Smiley,PageBreak,Iframe,FontSize,Font,Format,Styles,TextColor,BGColor,Maximize,ShowBlocks,About';
    // Simplify the dialog windows.
    config.removeDialogTabs = 'image:advanced;link:advanced';
    config.removePlugins = 'elementspath';
config.removePlugins = 'resize';
};
