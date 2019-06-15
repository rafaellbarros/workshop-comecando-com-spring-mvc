$('#confirmacaoExclusaoModal').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget);

    const codigoTitulo = button.data('codigo');
    const descricaoTitulo = button.data('descricao');

    const modal = $(this);

    const form = modal.find('form');

    let action = form.data('url-base');

    if (!action.endsWith('/')) {
        action += '/';
    }

    form.attr('action', action + codigoTitulo);

    modal.find('.modal-body').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?');

});

$(() => {
    $('[rel="tooltip"]').tooltip();
    $('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});

    $('.js-atualizar-status').on('click', (event) => {
        event.preventDefault();

        const botaoReceber = $(event.currentTarget);
        const urlReceber = botaoReceber.attr('href');

        const response = $.ajax({
            url: urlReceber,
            type: 'PUT'
        });

        response.done(function (e) {
            const codigoTitulo = botaoReceber.data('codigo');
            $(`[data-role=${codigoTitulo}]`).html(`<span class="badge badge-success">${e}</span>`)
            botaoReceber.hide();

        });

        response.fail(function (e) {
            console.error(e);
            alert('Erro recebendo cobrança');
        });



    })
})