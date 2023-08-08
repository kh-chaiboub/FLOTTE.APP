import { NavItem } from './nav-item/nav-item';

export const navItems: NavItem[] = [
  {
    navCap: 'Home',
  },
  {
    displayName: 'Dashboard',
    iconName: 'layout-dashboard',
    route: '/dashboard',
  },
  {
    navCap: 'Flotte',
  },
  {
    displayName: 'Tracking',
    iconName: 'rosette',
    route: '/ui-components/flotteMap',
  },
  {
    navCap: 'Véhicules',
  },
  {
    displayName: 'List Des Véhicules',
    iconName: 'car',
    route: '/ui-components/vehicle',
  },
  {
    displayName: 'List Alerts Des véhicules',
    iconName: 'alert-hexagon-filled',
    route: '/ui-components/listAlertsVl',
  },
  {
    navCap: 'Rapports',
  },
  {
    displayName: 'Dernieres Positions',
    iconName: 'clipboard-text',
    route: '/ui-components/lastPosition',
  },
  {
    displayName: 'Kilometrage',
    iconName: 'aperture',
    route: '/extra/sample-page',
  },
  {
    navCap: 'Devices',
  },
  {
    displayName: 'Liste des Balise',
    iconName: 'gps',
    route: '/ui-components/listDevice',
  },
  {
    navCap: 'Administrations',

  },
  {
    displayName: 'Entités',
    route: '/ui-components/refOrgans',
  },
  {
    displayName: 'Alerts',
    route: '/ui-components/alerts',
  },
  {
    navCap: 'Gestion Conducteur'
  },
  {
    displayName: 'Conducteurs',
    route: '/ui-components/conducteurs',
  },
  {
    navCap: 'Gestion des moyens roulent'
  },
  {
    displayName: 'Type moyens',
    route: '/ui-components/typeVl',
  },
  {
    displayName: 'Marque moyens',
    route: '/ui-components/marqueVl',
  },
  {
    displayName: 'Modéle moyens',
    route: '/ui-components/modelVl',
  },
  {
    navCap: 'Gestion Traceur GPS'
  },
  {
    displayName: 'Catégorie Traceur GPS',

    route: '/ui-components/categorieDevice',
  },
  {
    displayName: 'Marque Traceur GPS',

    route: '/ui-components/marqueDevice',
  },
  {
    displayName: 'Modéle Traceur GPS',
    route: '/ui-components/modelDevice',
  }

];
