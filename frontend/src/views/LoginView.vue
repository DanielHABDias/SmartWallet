<script setup lang="ts">
import TextBrand from '@/components/atoms/TextBrand.vue';
import Form from '@/components/molecules/Form.vue';
import { ref } from 'vue';
import { useAuth } from '@/stores/useAuth';

const auth = useAuth();
const showRegister = ref(true);

const listFields = ref([
    {
        name: 'name',
        label: 'Nome',
        type: 'text',
        placeholder: 'Digite seu nome de usuário',
        required: true
    },
    {
        name: 'email',
        label: 'E-mail',
        type: 'email',
        placeholder: 'Digite seu e-mail',
        required: true
    },
    {
        name: 'password',
        label: 'Senha',
        type: 'password',
        required: true
    }
]);

</script>

<template>
    <div class="auth">
        <TextBrand fontSize="2.5rem" />
        <Transition name="fade-slide" mode="out-in">
            <!-- REGISTER -->
            <div v-if="showRegister" key="register" class="register-container">
                <div class="register">
                    <Form
                        title="Cadastrar"
                        :list-fields="listFields"
                        @submit="(data) => auth.register(data.name!, data.email!, data.password!)"
                    />
                </div>
                <button class="toggle-btn mobile-toggle-btn" @click="showRegister = false">
                    Já tem conta? Entrar →
                </button>
                <div class="divider"></div>
                <div class="image-section">
                    <div class="image">
                        <img src="@/assets/images/icone_bits_smartwallet.png" alt="Register Image"/>
                    </div>
                    <button class="toggle-btn" @click="showRegister = false">
                        Já tem conta? Entrar →
                    </button>
                </div>
            </div>

            <!-- LOGIN -->
            <div v-else key="login" class="login-container">
                <div class="image-section">
                    <div class="image">
                        <img src="@/assets/images/icone_fogoroxo_smartwallet.png" alt="Login Image" />
                    </div>
                    <button class="toggle-btn" @click="showRegister = true">
                        ← Criar nova conta
                    </button>
                </div>
                <div class="divider"></div>
                <div class="login">
                    <Form
                        title="Entrar"
                        :list-fields="listFields.slice(1, 3)"
                        @submit="(data) => auth.login(data.email!, data.password!)"
                    />
                </div>
                <button class="toggle-btn mobile-toggle-btn" @click="showRegister = true">
                    ← Criar nova conta
                </button>
            </div>
        </Transition>
    </div>
</template>

<style scoped>
    .auth {
        width: 100%;
        height: auto;
        min-height: 100vh;
        margin: 0;
        padding: 20px 0;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        gap: 20px;
        overflow-x: hidden;
    }

    .register-container,
    .login-container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        gap: 0;
        width: 100%;
        box-sizing: border-box;
    }

    .register,
    .login {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        max-width: 100%;
        min-height: auto;
        padding: 0 20px;
        box-sizing: border-box;
    }

    .divider {
        display: none;
    }

    .image-section {
        display: none;
    }

    .image img {
        height: 20rem;
        width: auto;
    }

    .toggle-btn {
        padding: 12px 24px;
        font-size: 15px;
        font-weight: 600;
        color: #fff;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        border: none;
        border-radius: 8px;
        cursor: pointer;
        transition: all 0.3s ease;
        box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
    }

    .toggle-btn:hover {
        transform: translateY(-2px);
        box-shadow: 0 6px 20px rgba(102, 126, 234, 0.6);
    }

    .toggle-btn:active {
        transform: translateY(0);
    }

    .mobile-toggle-btn {
        display: block;
        width: calc(100% - 40px);
        margin: 20px 20px 0 20px;
        padding: 14px 20px;
        font-size: 14px;
    }

    /* Animações de transição */
    .fade-slide-enter-active,
    .fade-slide-leave-active {
        transition: all 0.5s ease;
    }

    .fade-slide-enter-from {
        opacity: 0;
        transform: translateX(30px);
    }

    .fade-slide-leave-to {
        opacity: 0;
        transform: translateX(-30px);
    }

    @media screen and (min-width: 1024px) {
        .auth {
            height: 100vh;
            padding: 0;
            gap: 0px;
        }

        .register-container,
        .login-container {
            flex-direction: row;
            gap: 40px;
        }

        .register,
        .login {
            min-width: 550px;
            min-height: 600px;
            width: auto;
            padding: 0;
        }

        .divider {
            display: block;
            width: 1px;
            height: 400px;
            background-color: #ccc;
        }

        .image-section {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            gap: 30px;
        }

        .mobile-toggle-btn {
            display: none;
        }
    }
</style>