document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        locale: 'es',
        initialView: 'dayGridWeek',
        firstDay: 1,
        events: function(fetchInfo, successCallback, failureCallback) {
            var idTeacher = '1'; // Cambia esto según el ID del docente que necesitas
            fetch('/clasesPorDocente?id_teacher=' + idTeacher)
                .then(response => response.json())
                .then(data => {
                    // Asegúrate de que el formato de `data` coincida con lo esperado por FullCalendar
                    var events = data.map(clase => ({
                        title: clase.title, // El título del evento
                        start: clase.start, // La fecha y hora de inicio
                        end: clase.end, // La fecha y hora de fin
                        backgroundColor: clase.backgroundColor, // Color de fondo del evento
                        borderColor: clase.borderColor // Color del borde del evento
                    }));
                    successCallback(events);
                })
                .catch(error => {
                    console.error('Error al obtener las clases:', error);
                    failureCallback(error);
                });
        },
        editable: false,
        selectable: false
    });

    calendar.render();
});
