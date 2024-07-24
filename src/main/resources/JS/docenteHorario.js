document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        locale: 'es',
        initialView: 'dayGridWeek',
        firstDay: 1,
        events: function(fetchInfo, successCallback, failureCallback) {
            var idTeacher = '1';
            fetch('/clasesPorDocente?id_teacher=' + idTeacher)
                .then(response => response.json())
                .then(data => {
                    var events = data.map(clase => ({
                        title: clase.title,
                        start: clase.start,
                        end: clase.end
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
