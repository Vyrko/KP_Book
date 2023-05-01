ymaps.ready(init);
function init() {
    let map = new ymaps.Map('map', {
        center:[53.91690612834677,27.595883410790716] ,
        zoom: 16
    });
    let placemark = new ymaps.Placemark([53.91690612834677,27.595883410790716], {}, {
    })
    map.controls.remove('geolocationControl'); // удаляем геолокацию
    map.controls.remove('searchControl'); // удаляем поиск
    map.controls.remove('trafficControl'); // удаляем контроль трафика
    map.controls.remove('fullscreenControl'); // удаляем кнопку перехода в полноэкранный режим
    map.controls.remove('rulerControl'); // удаляем контрол правил
    /*  map.behaviors.disable(['scrollZoom']); // отключаем скролл карты (опционально)*/
    map.geoObjects.add(placemark);
}
