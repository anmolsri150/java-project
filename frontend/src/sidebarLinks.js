export default [
  {
    name: 'Orders',
    icon: 'ti-home',
    collapsed: true,
    children: [{
      name: 'Create Order',
      path: '/dashboard'
    },
    {
      name: 'All Orders',
      path: '/orders'
    }]
  },
  {
    name: 'Cart',
    icon: 'ti-shopping-cart',
    path: '/cart'
  },
  {
    name: 'Order Statuses',
    icon: 'ti-server',
    collapsed: true,
    children: [{
      name: 'Get Status',
      path: '/status/list'
    },
    {
      name: 'Edit Status',
      path: '/status/edit'
    }]
  },
  {
    name: 'Menu',
    icon: 'ti-layout-menu-v',
    collapsed: true,
    children: [{
      name: 'Add Item',
      path: '/menu/list'
    },
    {
      name: 'Edit or Delete',
      path: '/menu/edit'
    }]
  }
]

