/**
 * @license Copyright (c) 2003-2022, CKSource Holding sp. z o.o. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function (config) {
    // Simplify the dialog windows.
    config.removeDialogTabs = 'image:advanced;link:advanced';
    config.removePlugins = 'elementspath';
    config.removePlugins = 'resize';
    config.format_tags = 'p;h1;h2;h3;pre';
    config.contenteditable = false;
    config.disableAutoInline = true;
    config.readOnly = true;
};
