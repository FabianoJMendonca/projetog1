function validaLogin() {
    let userTxt = localStorage.getItem("userLogged")

    if(!userTxt) {
        window.location = "index.html";
    }

/*     let user = JSON.parse(userTxt)

    document.getElementById("dadosUser").innerHTML = `${user.nome} <br> ${user.racf}` */

    listarAlarme()
 }


function listarAlarme() {
    let url = `http://localhost:8080/alarme/all` 

    fetch(url)
    .then(res => res.json())
    .then(res => exibirAlarme(res))
}


function exibirAlarme(lista) {
    let tabela = "<table> <tr> <th>Nome</th> <th>descricao</th> </tr>"

    for(i=0; i < lista.length ; i++) {
        tabela += `<tr> <td>${lista[i].nome}</td> <td>${lista[i].descricao}</td> </tr>`
    }

    tabela += "</table>"
    document.getElementById("tabela").innerHTML = tabela
}