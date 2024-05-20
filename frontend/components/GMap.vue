<template>
  <div>
    <div ref="google_map" id="map"></div>
  </div>
</template>

<script setup lang="ts">
import {centerGoogleMap} from "~/composables/google";

const props = defineProps<{
  lat?: Number;
  lng?: Number;
  title: string;
}>()

const emit = defineEmits(['update:coordinates']);
const google_map = ref<HTMLElement | null>(null);
onMounted(async () => {
  if (!google_map.value) throw new Error("Google Map DOM element not found");

  useGoogleMapHTML().value = google_map.value; // Save google map html
  initializeGoogleMap(google_map.value); // Init Google Map
  const googleMap = useGoogleMap().value;
  //googleMap.setCenter({ lat: props.lat, lng: props.lng });

  google.maps.event.addListener(googleMap, 'click', function(event) {
    centerGoogleMap(event.latLng.lat(), event.latLng.lng(), props.title);
    emit('update:coordinates', `${event.latLng.lat()},${event.latLng.lng()}`);
  });

});

</script>

<style scoped>
#map {
  height: 380px;
  width: 100%;

  /* Center Map */
  margin: auto;
}
</style>