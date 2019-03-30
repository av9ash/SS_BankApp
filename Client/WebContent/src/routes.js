import DashView from './components/Dash.vue'
import LoginView from './components/Login.vue'
import NotFoundView from './components/404.vue'

// Import Views - Dash
import DashboardView from './components/views/Dashboard.vue'
import TablesView from './components/views/Tables.vue'
import TasksView from './components/views/Tasks.vue'
import SettingView from './components/views/Setting.vue'
import DebitCreditView from './components/views/DebitCredit.vue'
import EmailPhoneTransfer from './components/views/EmailPhoneTransfer.vue'
import AccountTransfer from './components/views/AccountTransfer.vue'
import AccessView from './components/views/Access.vue'
import ServerView from './components/views/Server.vue'
import ReposView from './components/views/Repos.vue'
import createAppointmentView from './components/views/createAppointment'
import accountCreationView from './components/views/accountCreation'
import updateInformationView from './components/views/updateInformation'
import viewAppointmentView from './components/views/showAppointment'
import RequestProcessing from './components/views/RequestProcessing'

// Routes
const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: LoginView,
    children: [
      {
        path: 'login',
        alias: '',
        component: LoginView,
        name: 'Login',
        meta: {description: 'Login page for the application'}
      }
    ]
  },
  {
    path: '/',
    component: DashView,
    children: [
      {
        path: 'dashboard',
        alias: '',
        component: DashboardView,
        name: 'Dashboard',
        //meta: {description: 'Overview of environment'}
      }, {
        path: 'tables',
        component: TablesView,
        name: 'Tables',
        meta: {description: 'Simple and advance table in CoPilot'}
      }, {
        path: 'tasks',
        component: TasksView,
        name: 'Tasks',
        meta: {description: 'Tasks page in the form of a timeline'}
      }, {
        path: 'setting',
        component: SettingView,
        name: 'Settings',
        meta: {description: 'User settings page'}
      }, {
        path: 'access',
        component: AccessView,
        name: 'Access',
        meta: {description: 'Example of using maps'}
      }, {
        path: 'server',
        component: ServerView,
        name: 'Servers',
        meta: {description: 'List of our servers', requiresAuth: true}
      }, {
        path: 'repos',
        component: ReposView,
        name: 'Repository',
        meta: {description: 'List of popular javascript repos'}
      }, {
        path: 'createAppointment',
        component: createAppointmentView,
        name: 'Create Appointment',
        meta: {description: 'Creation of an appointment'}
      }, {
        path: 'accountCreation',
        component: accountCreationView,
        name: 'Account Creation',
        meta: {description: 'Creation of an account'}
      }, {
        path: 'updateInformation',
        component: updateInformationView,
        name: 'Update Information',
        meta: {description: 'Updating Information'}
	  }, {
        path: 'creditdebit',
        component: DebitCreditView,
        name: 'DebitCredit',
        meta: {description: 'Debit Credit Operation'}
      }, {
        path: 'accountTransfer',
        component: AccountTransfer,
        name: 'AccountTransfer',
        meta: {description: 'Account Transfer Operation'}
      }, {
        path: 'emailPhoneTransfer',
        component: EmailPhoneTransfer,
        name: 'EmailPhoneTransfer',
        meta: {description: 'Email/Phone Operation'}
      },
      {
        path: 'viewAppointment',
        component: viewAppointmentView,
        name: 'View Appointment',
        meta: {description: 'View Appointments'}
      },
      {
        path: 'approvedecline',
        component: RequestProcessing,
        name: 'Request Processing',
        meta: {description: 'Request Processing'}
      }
    ]
  }, {
    // not found handler
    path: '*',
    component: NotFoundView
  }
]

export default routes
