/**
 * 
 */

function confirmar(idcon) {
	let resposta = confirm("Confirmar a exclusão?")
	if(resposta === true){
		window.location.href ="delete?idcon=" + idcon
	}
}	