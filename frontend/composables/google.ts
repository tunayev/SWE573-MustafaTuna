import {useGoogleMap, useMarker} from "~/composables/states";

/**
 * Function will initialize Google Map with default settings
 *
 * @param map_html DOM HTML element in which Google Map will be initialized
 */
export const initializeGoogleMap = (map_html: HTMLElement): void => {
    if (!useGoogleMapHTML().value) {
        console.error("google_map_html is not defined");
        return;
    }

    if(useGoogleMap().value)
        return;


    // Initialize Google Map
    useGoogleMap().value = new google.maps.Map(map_html as HTMLElement, {
        center: { lat: 41, lng: 27 }, // Default map view position
        zoom: 4, // Default zoom

        styles: [
            {
                featureType: "poi",
                stylers: [{ visibility: "off" }],
            },
        ],

        streetViewControl: false,
        mapTypeControl: false,
        draggableCursor: "crosshair",
        fullscreenControl: false,
        minZoom: 8,

        restriction: {
            latLngBounds: {
                north: 85,
                south: -85,
                west: -180,
                east: 180,
            },
        },

        gestureHandling: "greedy", // Does not need 2 fingers to move on map when using touchscreen (Not working on Firefox Mobile. Safari, not sure.)
        keyboardShortcuts: false,
    });
};

// Function used to center Google Map to specific coordinates
export const centerGoogleMap = async (lat: number, lng: number, title: string | null = "asd") => {
    const googleMap = useGoogleMap().value;

    if (!googleMap) {
        console.error("google_map is not defined");
        return;
    }

    googleMap.setCenter({ lat, lng });

    const marker = useMarker();
    if(marker.value)
        marker.value = null;
    marker.value = new google.maps.Marker({
        position: new google.maps.LatLng(lat, lng), // Van Munching Hall,
        map: googleMap,
        title: title
    });

};