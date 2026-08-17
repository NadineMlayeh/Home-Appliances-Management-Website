/**
 * ElectroComfort - Toast Notifications & Custom Confirmation Modal System
 */

(function () {
    'use strict';

    // Ensure container exists
    function getToastContainer() {
        let container = document.getElementById('toast-container');
        if (!container) {
            container = document.createElement('div');
            container.id = 'toast-container';
            document.body.appendChild(container);
        }
        return container;
    }

    /**
     * Show a modern Toast Notification
     * @param {Object} opts { type: 'success'|'danger'|'warning'|'info', title: string, message: string, duration: number }
     */
    window.showToast = function (opts) {
        opts = opts || {};
        const type = opts.type || 'info';
        const duration = opts.duration || 3800;
        
        let title = opts.title;
        if (!title) {
            switch (type) {
                case 'success': title = 'Succès'; break;
                case 'danger':
                case 'error': title = 'Erreur'; break;
                case 'warning': title = 'Attention'; break;
                default: title = 'Information'; break;
            }
        }

        const message = opts.message || '';

        let iconHtml = '<i class="fas fa-info-circle"></i>';
        if (type === 'success') iconHtml = '<i class="fas fa-check-circle"></i>';
        if (type === 'danger' || type === 'error') iconHtml = '<i class="fas fa-exclamation-circle"></i>';
        if (type === 'warning') iconHtml = '<i class="fas fa-exclamation-triangle"></i>';

        const container = getToastContainer();

        const toast = document.createElement('div');
        toast.className = 'custom-toast toast-' + (type === 'error' ? 'danger' : type);
        toast.innerHTML = `
            <div class="toast-icon-wrapper">${iconHtml}</div>
            <div class="toast-content">
                <div class="toast-title">${title}</div>
                <div class="toast-message">${message}</div>
            </div>
            <button class="toast-close-btn" aria-label="Fermer">&times;</button>
            <div class="toast-progress-bar" style="animation-duration: ${duration}ms;"></div>
        `;

        container.appendChild(toast);

        const closeBtn = toast.querySelector('.toast-close-btn');
        let timerId;

        function removeToast() {
            if (timerId) clearTimeout(timerId);
            toast.classList.add('toast-hiding');
            setTimeout(function () {
                if (toast.parentNode) {
                    toast.parentNode.removeChild(toast);
                }
            }, 300);
        }

        closeBtn.addEventListener('click', removeToast);
        timerId = setTimeout(removeToast, duration);
    };

    /**
     * Show a Custom Modal Confirmation Dialog with Dark Backdrop Screen
     * @param {Object} opts { title: string, message: string, confirmText: string, cancelText: string, confirmBtnClass: string, iconClass: string, iconHtml: string, onConfirm: function, onCancel: function }
     * @returns {Promise<boolean>}
     */
    window.showConfirmModal = function (opts) {
        opts = opts || {};
        const title = opts.title || 'Confirmation';
        const message = opts.message || 'Êtes-vous sûr de vouloir effectuer cette action ?';
        const confirmText = opts.confirmText || 'Confirmer';
        const cancelText = opts.cancelText || 'Annuler';
        const confirmBtnClass = opts.confirmBtnClass || 'modal-btn-danger';
        const iconClass = opts.iconClass || (confirmBtnClass.includes('danger') ? 'modal-badge-danger' : 'modal-badge-primary');
        const iconHtml = opts.iconHtml || (confirmBtnClass.includes('danger') ? '<i class="fas fa-trash-alt"></i>' : '<i class="fas fa-question-circle"></i>');

        return new Promise(function (resolve) {
            let backdrop = document.getElementById('custom-modal-backdrop');
            if (!backdrop) {
                backdrop = document.createElement('div');
                backdrop.id = 'custom-modal-backdrop';
                backdrop.className = 'custom-modal-backdrop';
                document.body.appendChild(backdrop);
            }

            backdrop.innerHTML = `
                <div class="custom-modal-card">
                    <div class="modal-icon-badge ${iconClass}">
                        ${iconHtml}
                    </div>
                    <h3 class="modal-title">${title}</h3>
                    <p class="modal-message">${message}</p>
                    <div class="modal-actions">
                        <button type="button" class="modal-btn modal-btn-cancel" id="modal-cancel-btn">${cancelText}</button>
                        <button type="button" class="modal-btn ${confirmBtnClass}" id="modal-confirm-btn">${confirmText}</button>
                    </div>
                </div>
            `;

            const cancelBtn = backdrop.querySelector('#modal-cancel-btn');
            const confirmBtn = backdrop.querySelector('#modal-confirm-btn');

            function closeModal(result) {
                backdrop.classList.remove('modal-active');
                document.removeEventListener('keydown', handleKey);
                setTimeout(function () {
                    backdrop.innerHTML = '';
                    resolve(result);
                    if (result && typeof opts.onConfirm === 'function') opts.onConfirm();
                    if (!result && typeof opts.onCancel === 'function') opts.onCancel();
                }, 250);
            }

            function handleKey(e) {
                if (e.key === 'Escape') closeModal(false);
            }

            cancelBtn.addEventListener('click', function () { closeModal(false); });
            confirmBtn.addEventListener('click', function () { closeModal(true); });
            backdrop.addEventListener('click', function (e) {
                if (e.target === backdrop) closeModal(false);
            });

            document.addEventListener('keydown', handleKey);

            // Trigger animation
            requestAnimationFrame(function () {
                backdrop.classList.add('modal-active');
            });
        });
    };

    /**
     * Intercept standard window.alert to display a pro toast instead of native popup
     */
    window.alert = function (message) {
        window.showToast({
            type: 'info',
            message: message
        });
    };

    /**
     * Check URL query parameters on page load to display operation toast notifications
     */
    function initUrlMessageTriggers() {
        const urlParams = new URLSearchParams(window.location.search);
        const msg = urlParams.get('msg');
        const success = urlParams.get('success');
        const error = urlParams.get('error');

        let handled = false;

        if (msg) {
            handled = true;
            switch (msg) {
                case 'saved_client':
                    window.showToast({ type: 'success', title: 'Client enregistré', message: 'Le client a été enregistré avec succès.' });
                    break;
                case 'deleted_client':
                    window.showToast({ type: 'danger', title: 'Client supprimé', message: 'Le client a été supprimé avec succès.' });
                    break;
                case 'saved_article':
                    window.showToast({ type: 'success', title: 'Article enregistré', message: 'L\'article a été enregistré avec succès.' });
                    break;
                case 'deleted_article':
                    window.showToast({ type: 'danger', title: 'Article supprimé', message: 'L\'article a été supprimé avec succès.' });
                    break;
                case 'updated_quantity':
                    window.showToast({ type: 'success', title: 'Stock mis à jour', message: 'La quantité a été ajustée avec succès.' });
                    break;
                case 'saved_achat':
                    window.showToast({ type: 'success', title: 'Vente enregistrée', message: 'La vente a été enregistrée avec succès.' });
                    break;
                case 'deleted_achat':
                    window.showToast({ type: 'danger', title: 'Vente supprimée', message: 'La vente a été supprimée avec succès.' });
                    break;
                case 'saved_payment':
                    window.showToast({ type: 'success', title: 'Paiement enregistré', message: 'Les modifications du paiement ont été enregistrées.' });
                    break;
                case 'sent_message':
                    window.showToast({ type: 'success', title: 'Message envoyé', message: 'Votre message a été transmis avec succès.' });
                    break;
                default:
                    window.showToast({ type: 'info', message: msg });
                    break;
            }
        } else if (success) {
            handled = true;
            window.showToast({ type: 'success', message: success });
        } else if (error) {
            handled = true;
            window.showToast({ type: 'danger', message: error });
        }

        // Clean query parameter from URL bar without reload
        if (handled && window.history && window.history.replaceState) {
            const url = new URL(window.location.href);
            url.searchParams.delete('msg');
            url.searchParams.delete('success');
            url.searchParams.delete('error');
            const cleanUrl = url.pathname + (url.searchParams.toString() ? '?' + url.searchParams.toString() : '') + url.hash;
            window.history.replaceState(null, '', cleanUrl);
        }
    }

    /**
     * Delegated Click Listener for elements with [data-confirm]
     */
    function initDataConfirmDelegation() {
        document.addEventListener('click', function (e) {
            const confirmElem = e.target.closest('[data-confirm]');
            if (!confirmElem) return;

            e.preventDefault();
            e.stopPropagation();

            const confirmMsg = confirmElem.getAttribute('data-confirm') || 'Êtes-vous sûr de vouloir supprimer cet élément ?';
            const confirmTitle = confirmElem.getAttribute('data-title') || 'Confirmation de suppression';
            const confirmBtnText = confirmElem.getAttribute('data-btn-text') || 'Supprimer';

            window.showConfirmModal({
                title: confirmTitle,
                message: confirmMsg,
                confirmText: confirmBtnText,
                cancelText: 'Annuler',
                confirmBtnClass: 'modal-btn-danger',
                iconClass: 'modal-badge-danger',
                iconHtml: '<i class="fas fa-trash-alt"></i>'
            }).then(function (confirmed) {
                if (confirmed) {
                    if (confirmElem.tagName === 'A' && confirmElem.href) {
                        window.location.href = confirmElem.href;
                    } else if (confirmElem.form) {
                        confirmElem.form.submit();
                    } else if (confirmElem.getAttribute('onclick')) {
                        // If there was a custom function
                        eval(confirmElem.getAttribute('onclick'));
                    }
                }
            });
        }, true);
    }

    // Initialize on DOM ready
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', function () {
            initUrlMessageTriggers();
            initDataConfirmDelegation();
        });
    } else {
        initUrlMessageTriggers();
        initDataConfirmDelegation();
    }
})();
