document.addEventListener("DOMContentLoaded", function(event) {

    const showNavbar = (toggleId, navId, bodyId, headerId) =>{
    const toggle = document.getElementById(toggleId),
    nav = document.getElementById(navId),
    bodypd = document.getElementById(bodyId),
    headerpd = document.getElementById(headerId)
    
    // Validate that all variables exist
    if(toggle && nav && bodypd && headerpd){
    toggle.addEventListener('click', ()=>{
    // show navbar
    nav.classList.toggle('show')
    // change icon
    toggle.classList.toggle('bx-x')
    // add padding to body
    bodypd.classList.toggle('body-pd')
    // add padding to header
    headerpd.classList.toggle('body-pd')
    })
    }
    }
    
    showNavbar('header-toggle','nav-bar','body-pd','header')
    
    /*===== LINK ACTIVE =====*/
    const linkColor = document.querySelectorAll('.nav_link')
    
    function colorLink(){
    if(linkColor){
    linkColor.forEach(l=> l.classList.remove('active'))
    this.classList.add('active')
    }
    }
    linkColor.forEach(l=> l.addEventListener('click', colorLink))
    
    // Your code to run since DOM is loaded and ready
    });

Vue.createApp({

    data() {
        return {
            clientes:[],
            nombreCliente:"",
            apellidoCliente:"",
            mailCliente:"",
            nombreClienteEditado:"",
            apellidoClienteEditado:"",
            mailClienteEditado:"",
            idCliente:0,
            cliente1:[],
        }
    },

    created() {
        axios.get('http://localhost:8585/rest/clients')
            .then(datos => {

                this.clientes = datos.data._embedded.clients

            })

        axios.get('http://localhost:8585/rest/clients/1')
            .then(datos =>{
                this.cliente1 = datos.data
            })
    },

    methods: {
        crearCliente(){
            axios.post('http://localhost:8585/rest/clients',{
                nombre:this.nombreCliente,
                apellido:this.apellidoCliente,
                mail:this.mailCliente,
            })
            .then(function(response){
                console.log(response)
            })
            .catch(function(error){
                console.log(error)
            });
            
        },

        borrarCliente(id){
            console.log(id)
                axios.delete(id)
                // .then(response =>{
                //     this.clientes.splice(id[id.length-1],1)
                //     })
                    .then(function (loadData) {
                        location.reload(loadData);
                    })
                },
            
                

    },
    
    computed: {


    
},
}).mount('#app')
