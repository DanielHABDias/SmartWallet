import { useAuth } from '../stores/useAuth';

export default async function securityRouter(to: any, from: any, next: any) {
    const { isAuthenticated } = useAuth();
    if (to.name !== 'login' && !isAuthenticated) {
        next({ name: 'login' })
    } else {
        next()
    }
}