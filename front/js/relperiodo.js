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

    fetch("http://localhost:8080/evento/data", msg)
        .then(res => res.json())
        .then(res => preencheEventos(res));
}


function preencheEventos(res) {
    console.log(res);
    let tabelaEventos = '<table class="table table-sm"> <tr> <th>Equipamento</th> <th>Alarme</th> <th>Data</th> </tr>';

    for (i = 0; i < res.length; i++) {
        let dataAtual = new Date(res[i].dataevt).toLocaleDateString("pt-BR", { timeZone: 'UTC' })

        tabelaEventos = tabelaEventos + `<tr> 
                        <td> ${res[i].equipamento.hostname} </td> 
                        <td> ${res[i].alarme.descricao} </td> 
                        <td> ${dataAtual} </td> 
              </tr>`;
    }

    tabelaEventos += '</table>';
    document.getElementById("tabelaEV").innerHTML = tabelaEventos;

}