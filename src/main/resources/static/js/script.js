var closeNav = false;

function openClose(){
  if(closeNav){
    document.getElementById("sideNav").style.marginLeft = "-250px";
    document.getElementById("menuHamburger").style.marginLeft = "calc(0px + 10px)";
    document.getElementById("menu-hamburger-img").style.transform = "rotate(-360deg)";
    document.getElementById("menu-hamburger-img").classList.remove("im"); 
    document.getElementById("menu-hamburger-img").classList.remove("im-x-mark");
    document.getElementById("menu-hamburger-img").classList.add("im");
    document.getElementById("menu-hamburger-img").classList.add("im-menu");
    document.getElementById("menu-hamburger-img").style.transition = "all 0.6s";
    closeNav = false;
  }else{
    document.getElementById("sideNav").style.marginLeft = "0px";
    document.getElementById("menuHamburger").style.marginLeft = "205px";
    document.getElementById("menu-hamburger-img").style.transform = "rotate(-180deg)";
    document.getElementById("menu-hamburger-img").classList.remove("im"); 
    document.getElementById("menu-hamburger-img").classList.remove("im-menu");
    document.getElementById("menu-hamburger-img").classList.add("im");
    document.getElementById("menu-hamburger-img").classList.add("im-x-mark");
    document.getElementById("menu-hamburger-img").style.transition = "all 0.6s";
    closeNav = true;
  }
}

var chevronDown = document.getElementById('chevron-down')
var chevron = true

chevronDown.addEventListener('click', function(){
  if(chevron){
    chevron = false
    document.getElementById('arrowNotificacao').style.transform = "rotate(180deg)"
    document.getElementById('arrowNotificacao').style.transition = "all 0.3s";
    document.getElementById('notificacoes').style.display = "block"
    
  }else{
    chevron = true
    document.getElementById('arrowNotificacao').style.transform = "rotate(360deg)"
    document.getElementById('arrowNotificacao').style.transition = "all 0.3s";
    document.getElementById('notificacoes').style.display = "none"
  }
})