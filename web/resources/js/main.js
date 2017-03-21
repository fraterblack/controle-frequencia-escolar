$(function() {
    $('#side-menu').metisMenu();

    $(window).bind("load resize", function() {
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        
        if (height < 1) height = 1;
        $("#page-wrapper").css("min-height", (height) + "px");
    });
    
    $(window).bind("load", function() {
    	$(".loader").hide();
    });
    
    $(window).bind("unload", function() {
    	$(".loader").show();
    });

    var url = window.location;
    var element = $('ul.nav a').filter(function() {
        return this.href == url || url.href.indexOf(this.href) == 0;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
    
    //FECHA MENSAGENS
    $('.messages-container li.alert').click(function() {
    	$(this).slideUp();
    });
    //Autoclose messages
    var autoCloseSuccessMessages = setTimeout(function() {
    	$('.messages-container li.alert-success').slideUp();
    }, 2500);
    var autoCloseWarningMessages = setTimeout(function() {
    	$('.messages-container li.alert-warning').slideUp();
    }, 4000);
    var autoCloseErrorMessages = setTimeout(function() {
    	$('.messages-container li.alert-danger').slideUp();
    }, 7000);
    
    var formChanged;
    $(".form-registration input, .form-registration select").change(function() {
    	if (!formChanged) {
    		formChanged = true;
    		clearTimeout(autoCloseSuccessMessages);
    		clearTimeout(autoCloseWarningMessages);
    		clearTimeout(autoCloseErrorMessages);
    		
    		$(".messages-container li.alert").slideUp();
    	}
    });
    
    //MASCARAS
	$('.input-date').mask('00/00/0000');
	$('.input-date-year').mask('0000');
	$('.input-date-day-month').mask('00/00');
	$('.input-date-month-year').mask('00/0000');
	
	//DATA PICKERS
	//Data completa
	$('.input-date').datepicker({
		language: "pt-BR",
		format: 'dd/mm/yyyy', 
		todayBtn: "linked",
		autoclose: true, 
		todayHighlight: true,
		forceParse: false,
		showOnFocus: false
	});
	
	//Mês/ano
	$('.input-date-month-year').datepicker({
		language: "pt-BR",
		format: 'mm/yyyy', 
		viewMode: 'years', 
		minViewMode: 'months', 
		autoclose: true,
		forceParse: false,
		showOnFocus: false
	});
	
	//Dia/mês
	$('.input-date-day-month').datepicker({
		language: "pt-BR",
		format: 'dd/mm', 
		viewMode: 'months', 
		minViewMode: 'days', 
		autoclose: true,
		forceParse: false,
		showOnFocus: false
	});
	
	//Abre o calendário através do ícone localizado junto ao campo INPUT
	$(".open-calendar").click(function() {
		var container = $(this).parents(".input-group");
		var input = container.find(".input-date");
		
		input.datepicker("show");
		
		return false;
	});
	
	//ATIVA DESATIVA CHECKBOX FALTA JUSTIFICADA
	$("#page-wrapper").on("change", ".tabela-chamada .check-presenca", function() {
		var checkPresenca = $(this);
		var tr = checkPresenca.parents("tr");
		
		if (checkPresenca.is(":checked")) {
			tr.find(".check-falta-justificada").prop("checked", false).prop("disabled", true).addClass("disabled");
		} else {
			tr.find(".check-falta-justificada").prop("disabled", false).removeClass("disabled");
		}
	});
	
	$("#page-wrapper").on("click", ".tabela-chamada .check-falta-justificada.disabled", function() {
		
		var checkPresenca = $(this).prop("checked", false);
		
	});
	
	$(".form-signin .usuario").attr("placeholder", "Usuário");
	$(".form-signin .senha").attr("placeholder", "Senha");
	$(".input-search").attr("placeholder", "Busca").keydown(function(e) {
		if (e.which == 13) {
	        $(".button-search").focus();
			
			return false;
	    }
	});
});