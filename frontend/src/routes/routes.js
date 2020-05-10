import DashboardLayout from '../components/Dashboard/Layout/DashboardLayout.vue'
// GeneralViews
import NotFound from '../components/GeneralViews/NotFoundPage.vue'
// Dashboard pages
import Overview from 'src/components/Dashboard/Views/Dashboard/Overview.vue'
import Stats from 'src/components/Dashboard/Views/Dashboard/Stats.vue'

// Pages
import home from 'src/components/Dashboard/Views/Pages/home.vue'
import User from 'src/components/Dashboard/Views/Pages/UserProfile.vue'
import TimeLine from 'src/components/Dashboard/Views/Pages/TimeLinePage.vue'
import Login from 'src/components/Dashboard/Views/Pages/Login.vue'
import Register from 'src/components/Dashboard/Views/Pages/Register.vue'
import Lock from 'src/components/Dashboard/Views/Pages/Lock.vue'

// Components pages
import Buttons from 'src/components/Dashboard/Views/Components/Buttons.vue'
import GridSystem from 'src/components/Dashboard/Views/Components/GridSystem.vue'
import Panels from 'src/components/Dashboard/Views/Components/Panels.vue'
import SweetAlert from 'src/components/Dashboard/Views/Components/SweetAlert.vue'
import Notifications from 'src/components/Dashboard/Views/Components/Notifications.vue'
import Icons from 'src/components/Dashboard/Views/Components/Icons.vue'
import Typography from 'src/components/Dashboard/Views/Components/Typography.vue'

// Forms pages
import RegularForms from 'src/components/Dashboard/Views/Forms/RegularForms.vue'
import ExtendedForms from 'src/components/Dashboard/Views/Forms/ExtendedForms.vue'
import ValidationForms from 'src/components/Dashboard/Views/Forms/ValidationForms.vue'
import Wizard from 'src/components/Dashboard/Views/Forms/Wizard.vue'

// TableList pages
import RegularTables from 'src/components/Dashboard/Views/Tables/RegularTables.vue'
import ExtendedTables from 'src/components/Dashboard/Views/Tables/ExtendedTables.vue'
import PaginatedTables from 'src/components/Dashboard/Views/Tables/PaginatedTables.vue'
// Maps pages
import GoogleMaps from 'src/components/Dashboard/Views/Maps/GoogleMaps.vue'
import FullScreenMap from 'src/components/Dashboard/Views/Maps/FullScreenMap.vue'
import VectorMaps from 'src/components/Dashboard/Views/Maps/VectorMapsPage.vue'

// Calendar
import Calendar from 'src/components/Dashboard/Views/Calendar/CalendarRoute.vue'
// Charts
import Charts from 'src/components/Dashboard/Views/Charts.vue'
import hospital from '../components/Dashboard/Views/Pages/hospital'
import reportz from '../components/Dashboard/Views/Pages/reports'
import admission from '../components/Dashboard/Views/Pages/admission'
import pathology from '../components/Dashboard/Views/Pages/pathology'
import consultation from '../components/Dashboard/Views/Pages/consultation'
import pasttreatment from '../components/Dashboard/Views/Pages/pasttreatment'
import UserProfileEdit from '../components/Dashboard/Views/Pages/UserProfileEdit'
import MenuEdit from '../components/Dashboard/Views/Pages/MenuEdit'
import Menu from '../components/Dashboard/Views/Pages/Menu'
import Orders from '../components/Dashboard/Views/Pages/Orders'
import Order from '../components/Dashboard/Views/Pages/Order'
import Statuses from '../components/Dashboard/Views/Pages/Statuses'
import statusPage from '../components/Dashboard/Views/Pages/statusPage'
import StatusEdit from '../components/Dashboard/Views/Pages/StatusEdit'

let componentsMenu = {
  path: '/components',
  component: DashboardLayout,
  redirect: '/components/buttons',
  meta: {
    requiresAuth: true
  },
  children: [
    {
      path: 'buttons',
      name: 'Buttons',
      component: Buttons
    },
    {
      path: 'grid-system',
      name: 'Grid System',
      component: GridSystem
    },
    {
      path: 'panels',
      name: 'Panels',
      component: Panels
    },
    {
      path: 'sweet-alert',
      name: 'Sweet Alert',
      component: SweetAlert
    },
    {
      path: 'notifications',
      name: 'Notifications',
      component: Notifications
    },
    {
      path: 'icons',
      name: 'Icons',
      component: Icons
    },
    {
      path: 'typography',
      name: 'Typography',
      component: Typography
    }

  ]
}
let formsMenu = {
  path: '/forms',
  component: DashboardLayout,
  redirect: '/forms/regular',
  meta: {
    requiresAuth: true
  },
  children: [
    {
      path: 'regular',
      name: 'Regular Forms',
      component: RegularForms
    },
    {
      path: 'extended',
      name: 'Extended Forms',
      component: ExtendedForms
    },
    {
      path: 'validation',
      name: 'Validation Forms',
      component: ValidationForms
    },
    {
      path: 'wizard',
      name: 'Wizard',
      component: Wizard
    }
  ]
}

let tablesMenu = {
  path: '/table-list',
  component: DashboardLayout,
  redirect: '/table-list/regular',
  meta: {
    requiresAuth: true
  },
  children: [
    {
      path: 'regular',
      name: 'Regular Tables',
      component: RegularTables
    },
    {
      path: 'extended',
      name: 'Extended Tables',
      component: ExtendedTables
    },
    {
      path: 'paginated',
      name: 'Paginated Tables',
      component: PaginatedTables
    }]
}

let mapsMenu = {
  path: '/maps',
  component: DashboardLayout,
  redirect: '/maps/google',
  meta: {
    requiresAuth: true
  },
  children: [
    {
      path: 'google',
      name: 'Google Maps',
      component: GoogleMaps
    },
    {
      path: 'full-screen',
      name: 'Full Screen Map',
      component: FullScreenMap
    },
    {
      path: 'vector-map',
      name: 'Vector Map',
      component: VectorMaps
    }
  ]
}

let pagesMenu = {
  path: '/user',
  component: DashboardLayout,
  redirect: '/user/profile',
  meta: {
    requiresAuth: true
  },
  children: [
    {
      path: 'profile',
      name: 'Profile',
      component: User
    },
    {
      path: 'editprofile',
      name: 'Profile Edit',
      component: UserProfileEdit
    },
    {
      path: 'my-treatments',
      name: 'My Treatments',
      component: TimeLine
    },
    {
      path: 'past-treatments',
      name: 'Past Treatments',
      component: pasttreatment
    }
  ]
}

let loginPage = {
  path: '/login',
  name: 'Login',
  component: Login
}

let statuspage = {
  path: '/statusPage',
  name: 'Order Status',
  component: statusPage
}

let registerPage = {
  path: '/register',
  name: 'Register',
  component: Register
}

let lockPage = {
  path: '/lock',
  name: 'Lock',
  component: Lock
}

const routes = [
  {
    path: '/',
    component: DashboardLayout,
    meta: {
      requiresAuth: true
    },
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: home
      },
      {
        path: 'cart',
        name: 'Cart',
        component: reportz
      },
      {
        path: 'reports',
        name: 'Reports',
        component: reportz
      },
      {
        path: 'calendar',
        name: 'Calendar',
        component: Calendar
      },
      {
        path: 'charts',
        name: 'Charts',
        component: Charts
      },
      {
        path: 'orders',
        name: 'All Orders',
        component: Orders
      },
      {
        path: 'order/:id',
        name: 'Order Details',
        component: Order
      }
    ]
  },
  {
    path: '/status',
    component: DashboardLayout,
    redirect: '/status/list',
    meta: {
      requiresAuth: true
    },
    children: [
      {
        path: 'list',
        name: 'Statuses',
        component: Statuses
      },
      {
        path: 'edit',
        name: 'Status Edit',
        component: StatusEdit
      }
    ]
  },
  {
    path: '/orders',
    component: DashboardLayout,
    redirect: '/orders/list',
    meta: {
      requiresAuth: true
    },
    children: [
      {
        path: 'list',
        name: 'All Orders',
        component: Orders
      },
      {
        path: 'order',
        name: 'Order Info',
        component: Order
      }
    ]
  },
  {
    path: '/menu',
    component: DashboardLayout,
    redirect: '/menu/list',
    meta: {
      requiresAuth: true
    },
    children: [
      {
        path: 'list',
        name: 'All Menu Items',
        component: Menu
      },
      {
        path: 'edit',
        name: 'Edit Menu',
        component: MenuEdit
      }
    ]
  },
  {
    path: '/hospital',
    component: DashboardLayout,
    meta: {
      requiresAuth: true
    },
    redirect: '/hospital/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: hospital
      },
      {
        path: 'admission',
        name: 'Admission',
        component: admission
      },
      {
        path: 'pathology',
        name: 'Pathology',
        component: pathology
      },
      {
        path: 'consultation',
        name: 'Consultation',
        component: consultation
      }
    ]
  },
  componentsMenu,
  formsMenu,
  tablesMenu,
  mapsMenu,
  pagesMenu,
  statuspage,
  loginPage,
  registerPage,
  lockPage,
  {
    path: '/admin',
    component: DashboardLayout,
    redirect: '/admin/overview',
    meta: {
      requiresAuth: true
    },
    children: [
      {
        path: 'home',
        name: 'Home',
        component: home
      },
      {
        path: 'reports',
        name: 'Reports',
        component: reportz
      },
      {
        path: 'overview',
        name: 'Overview',
        component: Overview
      },
      {
        path: 'hospital',
        name: 'Lakewood Hospital',
        component: hospital
      },
      {
        path: 'stats',
        name: 'Stats',
        component: Stats
      }
    ]
  },
  {path: '*', component: NotFound}
]

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
 function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/


export default routes
