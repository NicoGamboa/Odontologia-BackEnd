window.addEventListener('load', function () {

   const formulario = document.querySelector('#add_new_odontologo');

   formulario.addEventListener('submit', function (event) {

       event.preventDefault();
       const formData = {

           matricula: document.querySelector('#matricula').value,

           apellido: document.querySelector('#apellido').value,

           nombre: document.querySelector('#nombre').value,



       };

       const url = '/odontologos';

       const settings = {

           method: 'POST',

           headers: {

               'Content-Type': 'application/json',

           },

           body: JSON.stringify(formData)

       }



        fetch(url, settings)
        .then(function(response){
        if(response.status==200){let successAlert = '<div class="alert alert-success alert-dismissible">' +

                                                             '<button type="button" class="close" ' +

                                                             'data-dismiss="alert">&times;</button>' +

                                                             '<strong></strong> Odontologo agregado  </div>'



                                                         document.querySelector('#response').innerHTML = successAlert;

                                                         document.querySelector('#response').style.display = "block";

                                                         resetUploadForm();
         }else if(response.status==403){

                           let errorAlert = '<div class="alert alert-danger alert-dismissible">' +

                                                                       '<button type="button" class="close"' +

                                                                       'data-dismiss="alert">&times;</button>' +

                                                                       '<strong> Error no tienes permiso para realizar esta accion</strong> </div>'



                                                        document.querySelector('#response').innerHTML = errorAlert;

                                                        document.querySelector('#response').style.display = "block";

                                                       resetUploadForm();

                           }else{
                           let errorAlert = '<div class="alert alert-danger alert-dismissible">' +

                                                                                        '<button type="button" class="close"' +

                                                                                        'data-dismiss="alert">&times;</button>' +

                                                                                        '<strong> Error intente nuevamente</strong> </div>'



                                                                         document.querySelector('#response').innerHTML = errorAlert;

                                                                         document.querySelector('#response').style.display = "block";

                                                                        resetUploadForm();}
                          return response.json();
                          })
                          });

              function resetUploadForm(){

                  document.querySelector('#matricula').value = " ";

                  document.querySelector('#apellido').value = " ";

                  document.querySelector('#nombre').value = " ";



              }
           })