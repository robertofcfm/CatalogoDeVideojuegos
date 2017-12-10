/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}
