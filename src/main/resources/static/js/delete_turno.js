function deleteBy(id)

          {

                   const url = '/turnos/'+ id;

                   const settings = {

                       method: 'DELETE'

                   }

                   fetch(url,settings)

                   .then(response => console.log(response))

                   let row_id = "#tr_" + id;

                   document.querySelector(row_id).remove();
                   }