function validaLogin() {
    let userTxt = localStorage.getItem("userLogged")

    if(!userTxt) {
        window.location = "index.html";
    }

    let user = JSON.parse(userTxt)

    document.getElementById("dadosUser").innerHTML = `${user.nome} <br> ${user.racf}`
    document.getElementById("fotoUser").innerHTML = `<img src="${user.linkFoto}" width="60" height="60" class="mr-3" alt="...">`

    listarEvento()
 }

 function listarEvento() {
    let url = `http://localhost:8080/evento/all` 

    fetch(url)
    .then(res => res.json())
    .then(res => exibirAlarme(res))
}

/* function obterDatas(pg) {
    let dataini = document.getElementById("dtinicio").value;
    let datafim = document.getElementById("dtfinal").value;

    let dataMsg = {
        dt1: dataini,
        dt2: datafim,
        pg: pg
    }

    let cabecalho = {
        method: 'POST',
        body: JSON.stringify(dataMsg),
        headers: {
            'Content-type': 'application/json'
        }
    }

    return cabecalho;
}*/

function gerarRelatorioEventos(pg) {

    let dataini = document.getElementById("dtinicio").value;
    let datafim = document.getElementById("dtfinal").value;

    let dataMsg = {
        dt1: dataini,
        dt2: datafim,
        pg: pg
    }

    let msg = {
        method: 'POST',
        body: JSON.stringify(dataMsg),
        headers: {
            'Content-type': 'application/json'
        }
    }

    fetch("http://localhost:8080/evento/datapg", msg)
        .then(res => res.json())
        .then(res => preencheEventos(res));
}


function preencheEventos(res) {
    console.log(res);
    let tabelaEventos = '<table class="table table-sm"> <tr> <th>Data</th> <th>Equipamento</th> <th>Alarme</th> </tr>';

    for (i = 0; i < res.content.length; i++) {
        let dataAtual = new Date(res.content[i].dataevt).toLocaleDateString("pt-BR", { timeZone: 'UTC' })

        tabelaEventos = tabelaEventos + `<tr> 
                        <td> ${dataAtual} </td> 
                        <td> ${res.content[i].equipamento.hostname} </td> 
                        <td> ${res.content[i].alarme.descricao} </td> 

              </tr>`;
    }

    tabelaEventos += '</table>';
    document.getElementById("tabelaEV").innerHTML = tabelaEventos;
    gerarPaginacao(res.totalPages);
  
}

function gerarPaginacao(totalPaginas) {
    if (totalPaginas < 2) {
        return;
    }

    let paginacao = ` 
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li class="page-item"><button class="page-link" onclick="paginar(0)">Primeira</button></li>`;

    for (let i = 0; i < totalPaginas; i++) {
        paginacao += `<li class="page-item"><button class="page-link" onclick="paginar(${i})">${i + 1}</button></li>`
    }

    paginacao += ` <li class="page-item"><button class="page-link" onclick="paginar(${totalPaginas-1})">Última</button></li> </ul> </nav> `;

    document.getElementById('paginacao').innerHTML = paginacao;
}

function paginar(pagina) {
    gerarRelatorioEventos(pagina)
}