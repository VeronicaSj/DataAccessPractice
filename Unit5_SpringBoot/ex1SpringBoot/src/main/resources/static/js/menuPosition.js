posicionarMenu();
$(window).scroll(function() {    
    posicionarMenu();
});
function posicionarMenu() {
    var altura_del_header = $('header').outerHeight(true);
    var altura_del_menu = $('nav').outerHeight(true);

	
    if ($(window).scrollTop() >= altura_del_header) {
        
        $('nav').css('position','fixed');
		$('nav').css('margin-top', -altura_del_header);
		$('nav').css('background-color','#D9E9F9');
		$('.msgcookie').hide();


    } else {
		$('nav').css('margin-top', '0');
        $('nav').css('position','');
		$('nav').css('background-color','#F1F1F1');        
    }
}
