// export const environment = {
//   production: true,
//   defaultauth: 'jwt-auth',
//   apiUrl: 'http://192.168.10.101:8080',
// };

export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080',
  //mapsUrl:'http://192.168.250.4:8002/bing/{z}/{x}/{y}.jpg',
   mapsUrl:'https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}',
  defaultauth: 'token',
  localitePositionVl:'http://localhost:2222', /// localite unite GR Fichier Json && Corection positon par Container keen_cerf
  // localitePositionCV:'http://192.168.1.127:8080/' //Container nominatim Nom--->position && position --->Nom
  localitePositionCV:'http://localhost:8080/' //Container nominatim Nom--->position && position --->Nom

};
// Container VM Routing machin login dev mots de pass 123
// portainer login admin mots de pass nassiri123456
