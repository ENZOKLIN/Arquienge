var estados = [
    "Acre",
    "Alagoas",
    "Amapá",
    "Amazonas",
    "Bahia",
    "Ceará",
    "Distrito Federal",
    "Espírito Santo",
    "Goiás",
    "Maranhão",
    "Mato Grosso",
    "Mato Grosso do Sul",
    "Minas Gerais",
    "Pará",
    "Paraíba",
    "Paraná",
    "Pernambuco",
    "Piauí",
    "Rio de Janeiro",
    "Rio Grande do Norte",
    "Rio Grande do Sul",
    "Rondônia",
    "Roraima",
    "Santa Catarina",
    "São Paulo",
    "Sergipe",
    "Tocantins"
]

for(i = 0; i < estados.length; i++){
    criarOption(estados[i], estados[i], "estado")
}

var diaNasc = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16',
        '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31']

for(i = 0; i < diaNasc.length; i++){
    criarOption(diaNasc[i], diaNasc[i], "diaNasc")
}

var mesNasc = ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro',
'Novembro', 'Dezembro']

for(i = 0; i < mesNasc.length; i++){
    if(i < 9){
        criarOption(mesNasc[i], "0"+(i+1), "mesNasc")
    }else{
        criarOption(mesNasc[i], (i+1), "mesNasc")
    }
}

for(i = 1920; i <= 2019; i++){
    criarOption(i, i, "anoNasc")
}

function criarOption(text, value, id){
    var option = document.createElement("option");
    option.text = text;
    option.value = value;
    var select = document.getElementById(id);
    select.appendChild(option);
}

var selectClicked = false;

function cliqueSelect(id){
    var select = document.getElementById(id)
    if(selectClicked){
        selectClicked = false
        select.style.backgroundImage = "url('/img/arrow-down-select.png')"
    } else {
        selectClicked = true
        select.style.backgroundImage = "url('/img/arrow-top-select.png')"
    }
}