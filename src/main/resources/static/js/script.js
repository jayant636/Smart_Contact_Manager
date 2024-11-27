console.log("script.js");


//Page Theme start

document.addEventListener('DOMContentLoaded',()=>{
 changeTheme();
})

let currentTheme = getTheme();
changeTheme();

function changeTheme(){
 document.querySelector("html").classList.add(currentTheme);

 changePageTheme(currentTheme,currentTheme);
 const changeThemeButton =  document.querySelector('#theme_change_button');
 const oldTheme = currentTheme;
// set the listener to change theme button
  changeThemeButton.addEventListener('click',()=>{
    console.log("Button Clicked");

//    remove the current theme
    if(currentTheme == "dark"){
      currentTheme = "light";
    }else{
    currentTheme = "dark";
    }

   changePageTheme(currentTheme,oldTheme);
  });
}

//set theme to localstorage
function setTheme(theme){
  localStorage.setItem("theme",theme);
}

//get theme from local storage
function getTheme(){
  let theme =  localStorage.getItem("theme");
  if(theme){
   return theme;
  }else{
  return "light";
  }
}

function changePageTheme(theme,oldTheme){

//    update the theme in localStorage
      setTheme(currentTheme);
      document.querySelector("html").classList.remove(oldTheme);
      document.querySelector('html').classList.add(theme);
      document.querySelector('#theme_change_button').querySelector("span").textContent = theme == "light" ? "Dark" : "Light";

      }

//  Page Theme End