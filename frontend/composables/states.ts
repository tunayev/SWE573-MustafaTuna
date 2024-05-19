export const useMobileMenu = () => useState<boolean>('mobile-menu', () => false)
export const useGoogleMap = () => useState<google.maps.Map | undefined>("google_map", undefined);
export const useGoogleMapHTML = () => useState<HTMLElement | undefined>("google_map_html", undefined);
export const useMarker = () => useState<google.maps.Marker | undefined>("marker", undefined);
