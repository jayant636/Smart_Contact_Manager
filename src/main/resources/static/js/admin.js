console.log("Admin User");

document.querySelector("#image_file_input").addEventListener('change',(e)=>{
   let file = e.target.files[0];
   let reader = new FileReader();
   reader.onload = function(){
     document.getElementById("upload_image_preview").src = reader.result;
   };
   reader.readAsDataURL(file);
});