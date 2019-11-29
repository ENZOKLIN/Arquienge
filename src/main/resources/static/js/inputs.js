// function addFieldsFerramentas(){
//
//     var cont = document.getElementById('cont1');
//
//     //Box que ficará todos os inputs.
//     var container = document.createElement('div');
//     container.classList.add('add-input');
//
//     //Box que ficará os inputs de ferramentas ou máquinas.
//     var material = document.createElement('div');
//     material.classList.add("material");
//     var inputMaterial = document.createElement('input');
//     inputMaterial.style.marginBottom = "10px";
//     inputMaterial.placeholder = "Alicate";
//     material.appendChild(inputMaterial);
//
//     var qtd = document.createElement('div');
//     qtd.classList.add("qtd");
//     var inputQtd = document.createElement('input');
//     inputQtd.style.marginBottom = "10px";
//     inputQtd.placeholder = "0";
//     inputQtd.type = "number";
//     qtd.appendChild(inputQtd)
//
//     container.appendChild(material);
//     container.appendChild(qtd);
//
//     cont.appendChild(container);
//
// }

// function addFieldsMaquinas(){
//
//     var cont = document.getElementById('cont2');
//
//     //Box que ficará todos os inputs.
//     var container = document.createElement('div');
//     container.classList.add('add-input');
//
//     //Box que ficará os inputs de ferramentas ou máquinas.
//     var material = document.createElement('div');
//     material.classList.add("material");
//     var inputMaterial = document.createElement('input');
//     inputMaterial.style.marginBottom = "10px";
//     inputMaterial.placeholder = "Trator";
//     material.appendChild(inputMaterial);
//
//     var qtd = document.createElement('div');
//     qtd.classList.add("qtd");
//     var inputQtd = document.createElement('input');
//     inputQtd.style.marginBottom = "10px";
//     inputQtd.placeholder = "0";
//     inputQtd.type = "number";
//     qtd.appendChild(inputQtd)
//
//     container.appendChild(material);
//     container.appendChild(qtd);
//
//     cont.appendChild(container);
//
// }
function addFieldsMaquinas() {
    var cont = document.getElementsByClassName("add-input");
    var encontrado = false;
    for(i = 0; i <= cont.length; i++){
        if(cont[i] == undefined){
            alert("Não é possível cadastrar mais de 5 Máquinas!");
        }
        else if(cont[i].style.display == 'none' && encontrado == false) {
            cont[i].style.display = 'block';
            encontrado = true;
            break;
        }
    }
}
function addFieldsFerramentas() {
    let cont = document.getElementsByClassName("add-input-2");
    let encontrado = false;
    for(x = 0; x <= cont.length; x++){
        if(cont[x] == undefined){
            alert("Não é possível cadastrar mais de 5 Ferramentas!");
        }
        else if(cont[x].style.display == 'none' && encontrado == false) {
            cont[x].style.display = 'block';
            encontrado = true;
            break;
        }
    }
}