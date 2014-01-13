<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>

<script type="text/javascript">
    jQuery(function ($) {
        function correctButtonClasses(grp){
            var group = $(grp);
            var form = group.parents('form').eq(0);
            var name = group.attr('data-toggle-name');
            var hidden = $('input[name="' + name + '"]', form);
            $('button', group).each(function () {
                var button = $(this);
                if (button.val() == hidden.val()) {
                    button.addClass('active');
                } else{
                    button.removeClass('active');
                }
            });
        }

        $('div.btn-group[data-toggle-name]').each(function () {
            var group = $(this);
            var form = group.parents('form').eq(0);
            var name = group.attr('data-toggle-name');
            var hidden = $('input[name="' + name + '"]', form);
            correctButtonClasses(group);
            $('button', group).each(function () {
                var button = $(this);
                button.on('click', function () {
                    hidden.val($(this).val());
                    correctButtonClasses(group);
                });
            });
        });

    });
</script>
